package ng.Chemist.exceptions.ChemistSalesManagementExceptions;

public class UserNotLoggedInException extends RuntimeException{
    public UserNotLoggedInException(String message){
        super(message);
    }
}
