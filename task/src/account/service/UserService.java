package account.service;

import account.errors.EmailExistError;
import account.errors.UnauthorizedError;
import account.models.UserInfo;
import account.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserInfo addUser(UserInfo userInfo) {
        if(repository.findByEmailIgnoreCase(userInfo.getEmail()).isPresent()){
            throw new EmailExistError("User exist!");
        }
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return repository.save(userInfo);
    }

    public UserInfo getUser(String username){
       UserInfo user  = repository.findByName(username);
       if(user==null){
           throw new UnauthorizedError("");
       }
        return user;
    }

    public List<UserInfo> getAll(){
        return repository.findAll();
    }

}
