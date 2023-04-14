package account.dto;

import account.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String lastname;
    private String email;

    public static UserDto mapToUserDTO(User user){
        return new UserDto(user.getId(), user.getName(), user.getLastname(), user.getEmail());
    }
}
