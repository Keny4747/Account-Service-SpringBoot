package account.service;

import account.entity.employee.Payment;
import account.entity.employee.PaymentRequest;
import account.entity.employee.PaymentResponse;
import account.repository.PaymentRepository;
import account.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class PaymentService {


    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(UserRepository userRepository,PaymentRepository paymentRepository) {
        //this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public PaymentResponse addPaymentEmployee(List<PaymentRequest> employee) {
        log.info("adding payrolls");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
        employee
                .forEach(e -> {
                    YearMonth yearMonth = YearMonth.parse(e.getPeriod(), formatter);
                    try {
                        paymentRepository.save(new Payment(e.getEmail(), yearMonth, e.getSalary()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

        return new PaymentResponse();
    }
    public List<Payment> findAll(){
        return paymentRepository.findAll();
    }
}
