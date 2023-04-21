package account.controllers;

import account.dto.UserDto;
import account.models.User;
import account.repository.UserRepository;
import account.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/signup")
    public ResponseEntity<UserDto> signUp(@Valid @RequestBody User user) {

        UserDto userDto = new ModelMapper().map(user, UserDto.class);
        userService.addUser(user);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/empl/payment")
    public ResponseEntity<String> greetings() {
        return ResponseEntity.ok("Good Bye and see you later");
    }


    @GetMapping("/get")
    public List<User> list() {
        return userService.getAll();
    }
}
