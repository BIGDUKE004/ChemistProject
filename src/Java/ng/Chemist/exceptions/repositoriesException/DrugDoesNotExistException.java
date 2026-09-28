package Java.ng.Chemist.exceptions.repositoriesException;

public class DrugDoesNotExistException extends RuntimeException{
    public DrugDoesNotExistException(String message){
        super(message);
    }
}
