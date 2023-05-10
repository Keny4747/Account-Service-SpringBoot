package account.entity.employee;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPaymentsResponse {

    private String name;
    private String lastname;
    private String period;
    private String salary;
}
