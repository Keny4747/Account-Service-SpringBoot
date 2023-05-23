package account.service;

import account.dto.UserDTO;
import account.entity.User;
import account.entity.user.UserDeleteResponse;
import account.entity.user.UserUpdate;
import account.exceptions.AdminCanNotDeleteException;
import account.exceptions.RoleCustomException;
import account.exceptions.RoleNotFoundException;
import account.exceptions.UserNotFoundException;
import account.repository.RoleRepository;
import account.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static account.util.Operation.GRANT;
import static account.util.Operation.REMOVE;
import static account.util.RoleAssignment.roleAssign;
import static account.util.RoleUtil.ADMIN;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AdminService(UserRepository userRepository,RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository =roleRepository;
    }

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public UserDeleteResponse deleteUser(String userEmail) {

        Optional<User> user = userRepository.findByEmailIgnoreCase(userEmail);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }

        if(user.get().getRoles().toString().contains(ADMIN.getValue())){
            throw new AdminCanNotDeleteException();
        }

        userRepository.delete(user.get());

       UserDeleteResponse userDeleteResponse = new UserDeleteResponse();
       userDeleteResponse.setUser(userEmail);
       return userDeleteResponse;
    }


    public UserDTO userRoleUpdate(UserUpdate userUpdate){
         Optional<User> user = userRepository.findByEmailIgnoreCase(userUpdate.getEmail());

        if(user.isEmpty()){
            throw new UserNotFoundException();
        }

        String roleUser = roleAssign(userUpdate.getRole());

        if(roleRepository.findByName(roleUser).isEmpty()){
            throw new RoleNotFoundException();
        }

        //DELETE OPERATION:
        if(userUpdate.getOperation().equals(REMOVE.name())){

            if(user.get().getRoles().toString().contains(userUpdate.getRole())){

                if(user.get().getRoles().toString().contains(ADMIN.getValue())){
                    throw new RoleCustomException("Can't remove ADMINISTRATOR role!");
                }
                if(user.get().getRoles().size()==1){
                    throw new RoleCustomException("The user must have at least one role!");
                }
                user.get().getRoles().remove(roleRepository.findByName(roleUser).orElseThrow());
                userRepository.save(user.get());
            }else {
                throw new RoleCustomException("The user does not have a role!");
            }
        }

        //GRANT OPERATION
        if(userUpdate.getOperation().equals(GRANT.name())){

            if(user.get().getRoles().toString().contains(ADMIN.getValue())){
                throw new RoleCustomException("The user cannot combine administrative and business roles!");
            }
            if(roleUser.equals(ADMIN.getValue())){
                throw new RoleCustomException("The user cannot combine administrative and business roles!");
            }
            user.get().getRoles().add(roleRepository.findByName(roleUser).orElseThrow());
            userRepository.save(user.get());
        }

        return new UserDTO(user.get());
    }
}
