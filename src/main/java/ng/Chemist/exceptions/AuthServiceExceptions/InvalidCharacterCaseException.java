package ng.Chemist.exceptions.AuthServiceExceptions;

public class InvalidCharacterCaseException extends RuntimeException{
    public InvalidCharacterCaseException (String message){
        super(message);
    }
}
