package account.dto;

import account.entity.User;
import account.entity.role.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private String lastname;
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    private List<String> roles;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.email = user.getEmail().toLowerCase();
        this.roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        Collections.reverse(this.roles);
    }


}
