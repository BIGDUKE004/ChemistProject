package Java.ng.Chemist.exceptions.ChemistSalesManagementExceptions;

public class InsufficientStockException extends RuntimeException{
    public InsufficientStockException(String message){
        super(message);
    }
}
