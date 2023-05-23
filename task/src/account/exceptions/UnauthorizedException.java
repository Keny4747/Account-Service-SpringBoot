package account.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Access Denied!")
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException() {
        super();
    }
}
