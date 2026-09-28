package Java.ng.Chemist.exceptions.AuthServiceExceptions;

public class InvalidUserNameException extends RuntimeException{
    public InvalidUserNameException(String message){
        super(message);
    }

}
