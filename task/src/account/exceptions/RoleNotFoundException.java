package account.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Role not found!")
public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(){
        super();
    }
}
