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

        User user = userRepository.findByEmail(userEmail);
        if(user == null){
            throw new UserNotFoundException();
        }

        if(user.getRoles().toString().contains("ROLE_ADMINISTRATOR")){
            throw new AdminCanNotDeleteException();
        }

        userRepository.delete(user);

       UserDeleteResponse userDeleteResponse = new UserDeleteResponse();
       userDeleteResponse.setUser(userEmail);
       return userDeleteResponse;
    }

    //TODO: finish this operation
    public UserDTO userRoleUpdate(UserUpdate userUpdate){
        User user = userRepository.findByEmail(userUpdate.getEmail());

        if(user == null){
            throw new UserNotFoundException();
        }

        String roleUser = roleAssign(userUpdate.getRole());

        if(roleRepository.findByName(roleUser).isEmpty()){
            throw new RoleNotFoundException();
        }

        //DELETE OPERATION:
        if(userUpdate.getOperation().equals(REMOVE.name())){

            if(user.getRoles().toString().contains(userUpdate.getRole())){

                if(user.getRoles().toString().contains(ADMIN.getValue())){
                    throw new RoleCustomException("Can't remove ADMINISTRATOR role!");
                }
                if(user.getRoles().size()==1){
                    throw new RoleCustomException("The user must have at least one role!");
                }
                //finally delete role user:)
                //TODO: delete currently role
                user.getRoles().remove(roleRepository.findByName(roleUser).orElseThrow());
                userRepository.save(user);
            }else {
                throw new RoleCustomException("The user does not have a role!");
            }
        }

        //GRANT OPERATION
        if(userUpdate.getOperation().equals(GRANT.name())){

            if(roleUser.equals(ADMIN.getValue())){
                throw new RoleCustomException("The user cannot combine administrative and business roles!");
            }
            user.getRoles().add(roleRepository.findByName(roleUser).orElseThrow());
            userRepository.save(user);
        }

        return new UserDTO(user);
    }
}
