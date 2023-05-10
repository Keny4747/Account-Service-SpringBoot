package account.service;

import account.entity.employee.Payment;
import account.entity.employee.PaymentAddedMessageResponse;
import account.entity.employee.PaymentRequest;
import account.entity.employee.PaymentUpdateMessageResponse;
import account.exceptions.PeriodException;
import account.exceptions.UserNotFoundException;
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
import java.util.Optional;

@Slf4j
@Service
public class PaymentService {

    private final DateTimeFormatter formatter= DateTimeFormatter.ofPattern("MM-yyyy");
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Autowired
    public PaymentService(UserRepository userRepository,PaymentRepository paymentRepository) {
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional(rollbackOn = DataIntegrityViolationException.class)
    public PaymentAddedMessageResponse addPaymentEmployee(List<PaymentRequest> employee) {
        log.info("adding payrolls");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");


        employee.forEach(e -> {
            if(userRepository.existsUserByEmailIgnoreCase(e.getEmail())){

                YearMonth yearMonth = YearMonth.parse(e.getPeriod(), formatter);

                Payment payment = new Payment(e.getEmail(), yearMonth, e.getSalary());

                if (paymentRepository.findByPeriodAndEmailIgnoreCase(payment.getPeriod(),payment.getEmail()).isEmpty()) {
                        paymentRepository.save(payment);
                } else {
                    throw new PeriodException();
                }

            }else{
                throw new UserNotFoundException();
            }
        });

        return new PaymentAddedMessageResponse();
    }
    @Transactional(rollbackOn = DataIntegrityViolationException.class)
    public PaymentUpdateMessageResponse updatePaymentEmployee(PaymentRequest paymentRequest){
        Optional<Payment> payment = paymentRepository.findByPeriodAndEmailIgnoreCase(YearMonth.parse(paymentRequest.getPeriod(),formatter), paymentRequest.getEmail());
        if(payment.isPresent()){
            payment.get().setSalary(paymentRequest.getSalary());
            paymentRepository.save(payment.get());
        }else{
           throw new UserNotFoundException();
        }
        return new PaymentUpdateMessageResponse();
    }


    public List<Payment> findAll(){
        return paymentRepository.findAll();
    }
}
