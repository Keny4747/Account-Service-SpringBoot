package account.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPassword {
    @JsonProperty("new_password")
    @Size(min = 12, message ="The password length must be at least 12 chars!")
    private String newPassword;
}
