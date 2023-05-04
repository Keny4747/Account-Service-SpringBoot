package account.repository;

import account.entity.employee.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Payment findByPeriodAndEmail(YearMonth yearMonth,String email );
}
