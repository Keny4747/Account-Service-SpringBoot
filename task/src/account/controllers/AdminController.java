package account.controllers;

import account.dto.UserDTO;
import account.entity.user.UserDeleteResponse;
import account.entity.user.UserUpdate;
import account.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService =adminService;
    }
    @GetMapping("/user")
    public List<UserDTO> getAll( ){
        return adminService.getAllUsers();
    }
    @DeleteMapping ("/user/{email}")
    public UserDeleteResponse deleteUser(@PathVariable("email") String userEmail){
        return adminService.deleteUser(userEmail);
    }

    @PutMapping("/user/role")
    public UserDTO updateRoleUser(@RequestBody UserUpdate userUpdate){

        return adminService.userRoleUpdate(userUpdate);

    }
}
