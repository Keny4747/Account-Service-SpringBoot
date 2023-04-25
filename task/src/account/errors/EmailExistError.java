package account.errors;

public class EmailExistError extends RuntimeException{

    public EmailExistError(String message){
        super(message);
    }
}
