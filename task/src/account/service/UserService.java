package account.service;

import account.dto.UserDTO;
import account.entity.User;
import account.entity.password.NewPassword;
import account.entity.password.PasswordResponse;
import account.exceptions.BreachedPasswordException;
import account.exceptions.UnauthorizedException;
import account.exceptions.UserExistException;
import account.exceptions.error.PasswordsNotDifferentException;
import account.repository.BreachedPasswordsRepository;
import account.repository.RoleRepository;
import account.repository.UserRepository;
import account.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final BreachedPasswordsRepository breachedPasswordsRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder,
                       BreachedPasswordsRepository breachedPasswordsRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.breachedPasswordsRepository = breachedPasswordsRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<UserDTO> registerAccount(User userRequest) {

        if(userRepository.findAll().isEmpty()){
            userRequest.setRoles(new ArrayList<>(Set.of(roleRepository.findByName("ROLE_ADMINISTRATOR").orElseThrow())));
        }else {
            userRequest.setRoles(new ArrayList<>(Set.of(roleRepository.findByName("ROLE_USER").orElseThrow())));
        }
        //End changes

        if (userRepository.existsUserByEmailIgnoreCase(userRequest.getEmail())) {
            throw new UserExistException();
        }
        if(breachedPasswordsRepository.getAllPasswords().contains(userRequest.getPassword())){
            throw new BreachedPasswordException("The password is in the hacker's database!");
        }
        userRequest.setPassword(encoder.encode(userRequest.getPassword()));

        //user.setEmail(user.getEmail().toLowerCase());
        //userRepository.save(userRequest);

        return ResponseEntity.ok(new UserDTO(userRepository.save(userRequest)));
        //return ResponseEntity.ok(getUserDto(userRepository.save(userRequest)));
    }

    public PasswordResponse changePassword(NewPassword newPassword, UserDetailsImpl userDetails){
        if(breachedPasswordsRepository.getAllPasswords().contains(newPassword.getNewPassword())){
            throw new BreachedPasswordException("The password is in the hacker's database!");
        }

        User user = userRepository.findByEmail(userDetails.getUsername());
        if(user == null ){
            throw  new UnauthorizedException();
        }

        if(encoder.matches(newPassword.getNewPassword(),user.getPassword())){
            throw new PasswordsNotDifferentException("The passwords must be different!");
        }

        user.setPassword(encoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);


        return new PasswordResponse(user.getEmail().toLowerCase(),"The password has been updated successfully");
    }

}
