package account.service;

import account.entity.employee.Payment;
import account.repository.PaymentRepository;
import account.security.UserDetailsImpl;
import account.util.DatePaymentFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmployeeService {
    private final DateTimeFormatter formatter= DateTimeFormatter.ofPattern("MM-yyyy");

    private final PaymentRepository paymentRepository;

    public EmployeeService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;

    }

    //TODO: change the format of period to "MM/yyyy"
    public ResponseEntity<?> getUserInfo(UserDetailsImpl userDetails, String period) {

        if (period == null) {

            List<Payment> paymentList = paymentRepository.findPaymentByEmail(userDetails.getUsername());
            return new ResponseEntity<>(paymentList, HttpStatus.OK);
        } else {
            Payment payment = paymentRepository.findByPeriodAndEmail(YearMonth.parse(period,formatter), userDetails.getUsername());
            return new ResponseEntity<>(payment, HttpStatus.OK);
        }
    }

}
