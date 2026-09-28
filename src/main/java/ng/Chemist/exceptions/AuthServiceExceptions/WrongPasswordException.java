package ng.Chemist.exceptions.AuthServiceExceptions;

public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException(String message){
        super(message);
    }
}
