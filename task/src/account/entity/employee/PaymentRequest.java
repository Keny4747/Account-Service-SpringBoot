package account.entity.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    @JsonProperty("employee")
    private String email;

    @Pattern(regexp = "^(0[1-9]|1[0-2])-\\d{4}$", message = "El mes debe estar entre 01 y 12 en el formato MM-yyyy")
    private String period;

    @Min(value = 0)
    @NotNull
    private Long salary;
}
