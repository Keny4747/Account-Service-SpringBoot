package account.exceptions.error;

public class PasswordsNotDifferentException extends RuntimeException{
    public PasswordsNotDifferentException(String message){
        super(message);
    }
}
