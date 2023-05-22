package account.entity.user;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class UserDeleteResponse {

    private String user;
    private final String status;

    public UserDeleteResponse() {
        this.status = "Deleted successfully!";
    }
}
