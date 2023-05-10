package account.controllers;

import account.security.UserDetailsImpl;
import account.service.EmployeeService;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

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
    public Object getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails,
                              @RequestParam(value = "period",defaultValue = "")
                              String period) {

        if(period.isEmpty()) {
            return employeeService.getUserPayments(userDetails);
        }else {
            return employeeService.getUserInfo(userDetails, period);
        }
    }


}
