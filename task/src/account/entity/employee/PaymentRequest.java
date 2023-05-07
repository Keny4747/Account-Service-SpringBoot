package account.entity.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    @JsonProperty("employee")
    private String email;

    @DateTimeFormat(pattern = "MM-yyyy")
    private String period;

    @Min(value = 0)
    @NotNull
    private Long salary;
}
