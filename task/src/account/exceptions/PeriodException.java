package account.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Period already registered in the database")
public class PeriodException extends RuntimeException {

    public PeriodException(){
        super();
    }
}
