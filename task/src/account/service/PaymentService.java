package account.service;

import account.entity.employee.Payment;
import account.entity.employee.PaymentRequest;
import account.entity.employee.PaymentMessageResponse;
import account.repository.PaymentRepository;
import account.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    //TODO: need to complete this, currently this found the payment in the BD and dont save the same payment
    @Transactional(rollbackOn = DataIntegrityViolationException.class)
    public PaymentMessageResponse addPaymentEmployee(List<PaymentRequest> employee) {
        log.info("adding payrolls");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");

        employee.forEach(e -> {
            YearMonth yearMonth = YearMonth.parse(e.getPeriod(), formatter);
            Payment payment = new Payment(e.getEmail(), yearMonth, e.getSalary());

            if (paymentRepository.findByPeriodAndEmail(payment.getPeriod(), payment.getEmail()) == null) {
                try {
                    paymentRepository.save(payment);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                throw new DataIntegrityViolationException("Error!");
            }
        });

        return new PaymentMessageResponse();
    }



    public List<Payment> findAll(){
        return paymentRepository.findAll();
    }
}
