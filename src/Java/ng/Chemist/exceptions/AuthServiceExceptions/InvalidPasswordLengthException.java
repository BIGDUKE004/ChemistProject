package Java.ng.Chemist.exceptions.AuthServiceExceptions;

public class InvalidPasswordLengthException extends RuntimeException{
    public InvalidPasswordLengthException(String message){
        super(message);
    }
}
