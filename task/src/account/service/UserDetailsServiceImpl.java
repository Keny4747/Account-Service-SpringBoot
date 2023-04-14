package account.service;

import account.dto.UserDto;
import account.models.User;
import account.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(email.toLowerCase()))
                .findAny().orElseThrow(() -> new UsernameNotFoundException("Email " + email + " Is not found"));
        return new UserDetailsImpl(user);
    }
    public User saveUser(User user){
        Optional<User> user1 = userRepository.findAll()
                .stream().filter(it->it.getName().equals(user.getName()))
                .findAny();
        if(user1.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User exist!");
        }
        user.setName(user.getName());
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        UserDto.mapToUserDTO(user);
        return user;
    }

    public User getAllPayment(String email){
        return userRepository.findAll().stream()
                .filter(it->it.getEmail().equals(email))
                .findAny().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
