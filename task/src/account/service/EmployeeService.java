package account.service;

import account.entity.User;
import account.entity.employee.GetPaymentsResponse;
import account.entity.employee.Payment;
import account.exceptions.PeriodException;
import account.repository.PaymentRepository;
import account.repository.UserRepository;
import account.security.UserDetailsImpl;
import account.util.DatePaymentFormat;
import account.util.SalaryFormatter;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final DateTimeFormatter formatter= DateTimeFormatter.ofPattern("MM-yyyy");

    private final PaymentRepository paymentRepository;

    private final UserRepository userRepository;

    private final SalaryFormatter salaryFormatter;

    private final DatePaymentFormat dateTimeFormatter;


    public EmployeeService(PaymentRepository paymentRepository,UserRepository userRepository,
                           SalaryFormatter salaryFormatter,DatePaymentFormat dateTimeFormatter) {
        this.paymentRepository = paymentRepository;
        this.userRepository =userRepository;
        this.salaryFormatter = salaryFormatter;
        this.dateTimeFormatter = dateTimeFormatter;
    }


    public List<GetPaymentsResponse> getUserPayments(UserDetailsImpl userDetails) {

        User user = userRepository.findByEmail(userDetails.getUsername());
           return paymentRepository.findPaymentByEmailIgnoreCaseOrderByPeriodDesc(userDetails.getUsername())
                   .stream()
                   .map(entity ->
                           GetPaymentsResponse.builder()
                                   .name(user.getName())
                                   .lastname(user.getLastname())
                                   .period(dateTimeFormatter.getFormattedPeriod(entity.getPeriod()))
                                   .salary(salaryFormatter.getFormattedSalary(entity.getSalary()))
                                   .build()
                           )
                   .collect(Collectors.toList());
    }

    public GetPaymentsResponse getUserInfo(UserDetailsImpl userDetails, String period) {
            if(!period.matches("^(0[1-9]|1[0-2])-\\d{4}$")){
                throw new PeriodException();
            }
            User user = userRepository.findByEmail(userDetails.getUsername());

            Payment payment = paymentRepository.findByPeriodAndEmailIgnoreCase(YearMonth.parse(period,formatter), userDetails.getUsername())
                    .orElseThrow(EntityNotFoundException::new);
            return GetPaymentsResponse.builder()
                    .name(user.getName())
                    .lastname(user.getLastname())
                    .salary(salaryFormatter.getFormattedSalary(payment.getSalary()))
                    .period(dateTimeFormatter.getFormattedPeriod(payment.getPeriod()))
                    .build();
    }

}
