package account.controllers;

import account.dto.UserDTO;
import account.models.UserInfo;
import account.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HomeController {


    private final UserService userservice;
    public HomeController(UserService userService){
        this.userservice = userService;
    }


    @PostMapping("/auth/signup")
    public ResponseEntity<UserDTO> addNewUser(@Valid @RequestBody UserInfo userInfo) {
        UserDTO userDTO = new ModelMapper().map(userservice.addUser(userInfo), UserDTO.class);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/empl/payment")
    public ResponseEntity<UserDTO> getInfo(@AuthenticationPrincipal UserDetails details) {
        UserInfo user = userservice.getUser(details.getUsername());

        return ResponseEntity.ok(new ModelMapper().map(user,UserDTO.class));

    }
    @GetMapping("/all")
    public List<UserInfo> getAll() {
       return userservice.getAll();
    }

}
