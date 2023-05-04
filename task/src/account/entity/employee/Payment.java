package account.entity.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("employee")
    private String email;

    @DateTimeFormat(pattern = "MM-yyyy")
    private YearMonth period;

    @Min(value = 0)
    private Long salary;
    public Payment(String email, YearMonth period, Long salary){
        this.email = email;
        this.period = period;
        this.salary = salary;
    }
}
