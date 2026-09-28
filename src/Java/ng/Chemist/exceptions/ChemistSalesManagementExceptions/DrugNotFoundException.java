package Java.ng.Chemist.exceptions.ChemistSalesManagementExceptions;

public class DrugNotFoundException extends RuntimeException{
    public DrugNotFoundException(String message){
        super(message);
    }
}
