package account.entity.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String email;

    @Column( unique=true)
    private String period;

    @Min(value = 0)
    private Long salary;
}
