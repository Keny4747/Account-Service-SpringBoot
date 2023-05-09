package account.entity.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {


    @JsonProperty("employee")
    private String email;

    @DateTimeFormat(pattern = "MM-yyyy")
    private String period;

    private Long salary;
}
