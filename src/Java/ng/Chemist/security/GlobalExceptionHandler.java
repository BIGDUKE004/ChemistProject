package Java.ng.Chemist.security;

import Java.ng.Chemist.exceptions.AuthServiceExceptions.*;
import Java.ng.Chemist.exceptions.ChemistSalesManagementExceptions.DrugNotFoundException;
import Java.ng.Chemist.exceptions.ChemistSalesManagementExceptions.InsufficientStockException;
import Java.ng.Chemist.exceptions.ChemistSalesManagementExceptions.UserNotLoggedInException;
import Java.ng.Chemist.exceptions.repositoriesException.DrugDoesNotExistException;
import Java.ng.Chemist.exceptions.repositoriesException.SalesRecordNotFoundException;
import Java.ng.Chemist.exceptions.repositoriesException.SameIdException;
import ng.Chemist.exceptions.AuthServiceExceptions.*;
import Java.ng.Chemist.exceptions.ChemistDrugManagementServiceException.FillInEveryInformationException;
import ng.Chemist.exceptions.ChemistSalesManagementExceptions.*;
import ng.Chemist.exceptions.repositoriesException.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            InvalidUserNameException.class,
            InvalidPasswordLengthException.class,
            InvalidCharacterCaseException.class,
            NoDigitIncludedException.class,
            FillInEveryInformationException.class,
            InsufficientStockException.class,
            SameIdException.class
    })
    public ResponseEntity<Map<String, String>> handleBadRequest(RuntimeException ex) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler({
            AccountNotFoundException.class,
            WrongPasswordException.class,
            UserNotLoggedInException.class
    })
    public ResponseEntity<Map<String, String>> handleUnauthorized(RuntimeException ex) {
        return build(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler({
            DrugNotFoundException.class,
            DrugDoesNotExistException.class,
            SalesRecordNotFoundException.class
    })
    public ResponseEntity<Map<String, String>> handleNotFound(RuntimeException ex) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleOthers(Exception ex) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
    }

    private ResponseEntity<Map<String, String>> build(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of("error", message));
    }
}
