package account.service;

import account.entity.User;
import account.repository.PaymentRepository;
import account.repository.UserRepository;
import account.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    public EmployeeService(UserRepository userRepository, PaymentRepository paymentRepository) {
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }


    public ResponseEntity<User> getUserInfo(UserDetailsImpl userDetails,String period) {

        if(period.length()>0){
            
        }

        Optional<User> user = userRepository.findByEmailIgnoreCase(userDetails.getUsername());
        //System.out.println(user);
        return ResponseEntity.of(user);
    }

}
