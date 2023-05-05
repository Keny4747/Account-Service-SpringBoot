package account.controllers;

import account.entity.User;
import account.security.UserDetailsImpl;
import account.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/empl")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /*
        @GetMapping("/payment")
        public ResponseEntity<User> getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
            return employeeService.getUserInfo(userDetails);
        }

     */
    @GetMapping("/payment")
    public /*ResponseEntity<User>*/String getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestParam(value = "period",required = false) String period) {
        //return employeeService.getUserInfo(userDetails);
        return period;
    }
}
