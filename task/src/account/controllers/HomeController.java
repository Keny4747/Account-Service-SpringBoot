package account.controllers;

import account.dto.UserDto;
import account.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class HomeController {

    @PostMapping("/auth/signup")
    ResponseEntity<UserDto> signupUser(@Valid @RequestBody User user){

        UserDto userDto= new ModelMapper().map(user, UserDto.class);

        return new ResponseEntity<>(userDto,HttpStatus.OK);

    }
}
