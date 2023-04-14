package account.controllers;

import account.dto.UserDto;
import account.models.User;
import account.service.UserDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final UserDetailsServiceImpl userDetailsService;

    public HomeController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody User user){
        var user1 =  userDetailsService.saveUser(user);
        return ResponseEntity.ok(UserDto.mapToUserDTO(user1));
    }
    @GetMapping("/empl/payment")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal UserDetails userDetails){
        var user = userDetailsService.getAllPayment(userDetails.getUsername());
        return ResponseEntity.ok(UserDto.mapToUserDTO(user));
    }
}
