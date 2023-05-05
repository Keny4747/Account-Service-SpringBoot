package account.entity.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {


    @JsonProperty("employee")
    private String email;

    private YearMonth period;

    private Long salary;
}
