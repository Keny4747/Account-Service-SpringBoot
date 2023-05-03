package account.controllers;

import account.entity.employee.Employee;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/acct")
public class PaymentController {
    @PostMapping("/payments")
    public void addPaymentUser(@RequestBody Employee [] employee){

    }

    @PutMapping("/payments/")
    public void updatePaymentUser(){}
}
