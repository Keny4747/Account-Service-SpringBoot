package account.repository;

import account.entity.employee.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Payment findByPeriodAndEmail(YearMonth yearMonth,String email );

    List<Payment> findPaymentByEmail(String email);
}
