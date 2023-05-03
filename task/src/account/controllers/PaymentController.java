package account.controllers;

import account.entity.employee.Employee;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/acct")
@Validated
public class PaymentController {
    @PostMapping("/payments")
    public List<Employee> addPaymentUser(@Valid @RequestBody Employee [] employee){
        return Arrays.stream(employee).toList();
    }

    @PutMapping("/payments/")
    public void updatePaymentUser(){}
}
