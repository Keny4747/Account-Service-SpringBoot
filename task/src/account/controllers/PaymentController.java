package account.controllers;

import account.entity.employee.Payment;
import account.entity.employee.PaymentRequest;
import account.entity.employee.PaymentAddedMessageResponse;
import account.entity.employee.PaymentUpdateMessageResponse;
import account.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/acct")
@Validated
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payments")
    public PaymentAddedMessageResponse addPaymentUser(@RequestBody List<@Valid PaymentRequest> employees) {
        return paymentService.addPaymentEmployee(employees);

    }
    @PutMapping("/payments")
    public PaymentUpdateMessageResponse updatePaymentUser(@Valid @RequestBody PaymentRequest paymentRequest) {
        return paymentService.updatePaymentEmployee(paymentRequest);
    }
    @GetMapping("/all")
    public List<Payment> geAllPayments(){
        return paymentService.findAll();
    }
}
