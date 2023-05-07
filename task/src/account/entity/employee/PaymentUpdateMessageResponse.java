package account.entity.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentUpdateMessageResponse {
    private static final String DEFAULT_STATUS = "Updated successfully!";
    private String status;

    {
        this.status = DEFAULT_STATUS;
    }
}
