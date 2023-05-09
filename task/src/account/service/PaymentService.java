package account.service;

import account.entity.employee.Payment;
import account.entity.employee.PaymentRequest;
import account.entity.employee.PaymentAddedMessageResponse;
import account.entity.employee.PaymentUpdateMessageResponse;
import account.exceptions.PeriodException;
import account.exceptions.UserExistException;
import account.exceptions.UserNotFoundException;
import account.repository.PaymentRepository;
import account.repository.UserRepository;
import javassist.NotFoundException;
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

    @Autowired
    public PaymentService(UserRepository userRepository,PaymentRepository paymentRepository) {
        //this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }


    @Transactional(rollbackOn = DataIntegrityViolationException.class)
    public PaymentAddedMessageResponse addPaymentEmployee(List<PaymentRequest> employee) {
        log.info("adding payrolls");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");

        employee.forEach(e -> {
            YearMonth yearMonth = YearMonth.parse(e.getPeriod(), formatter);
            Payment payment = new Payment(e.getEmail(), yearMonth, e.getSalary());

            if (paymentRepository.findByPeriodAndEmail(payment.getPeriod(), payment.getEmail()).isEmpty()) {
                try {
                    paymentRepository.save(payment);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                throw new PeriodException();
            }
        });

        return new PaymentAddedMessageResponse();
    }
//TODO: formatter error
    public PaymentUpdateMessageResponse updatePaymentEmployee(PaymentRequest paymentRequest){
        Optional<Payment> payment = paymentRepository.findByPeriodAndEmail(YearMonth.parse(paymentRequest.getPeriod(),formatter), paymentRequest.getEmail());
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
