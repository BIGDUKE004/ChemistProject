package ng.chemist.serviceTest;

import ng.Chemist.Data.model.Drug;
import ng.Chemist.Data.repositories.DrugRepository;
import ng.Chemist.Data.repositories.DrugRepositoryImpl;
import ng.Chemist.Data.repositories.UserRepositoryImpl;
import ng.Chemist.dtos.request.authServiceRequest.LoginUserRequest;
import ng.Chemist.dtos.request.authServiceRequest.RegisterUserRequest;
import ng.Chemist.dtos.request.chemistDrugManagementServiceRequest.*;
import ng.Chemist.dtos.response.authServiceResponse.LoginUserResponse;
import ng.Chemist.dtos.response.authServiceResponse.RegisterUserResponse;
import ng.Chemist.dtos.response.chemistDrugManagementServiceResponse.*;
import ng.Chemist.exceptions.ChemistDrugManagementServiceException.FillInEveryInformationException;
import ng.Chemist.exceptions.repositoriesException.DrugDoesNotExistException;
import ng.Chemist.service.AuthServiceImpl;
import ng.Chemist.service.ChemistDrugManagementService;
import ng.Chemist.service.ChemistDrugManagementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ChemistDrugManagementServiceTest {
    private DrugRepository drugRepository;
    @BeforeEach
    public void setUp(){
        drugRepository = new DrugRepositoryImpl();
    }
    @Test
    public void chemistIsLoggedIn_chemistAddsDrugToTheSystem(){
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Registration Successful", response.getMessage());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("Elijah");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Login successful", login.getMessage());

        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setBatchNumber("EMP2026001");
        addDrugRequest.setBrandName("Emzor Paracetamol");
        addDrugRequest.setDosage("Tablet");
        addDrugRequest.setStrength("500mg");
        addDrugRequest.setQuantityInStock(100);
        addDrugRequest.setPrice(500);
        addDrugRequest.setId(101);
        addDrugRequest.setGenericName("paracetamol");
        addDrugRequest.setManufacturer("Emzor");
        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
        ChemistDrugManagementService service = new ChemistDrugManagementServiceImpl(drugRepository);
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
        assertEquals("Drug added successfully", drugResponse.getMessage());
    }

    @Test
    public void chemistIsLoggedIn_chemistAddsDrugToTheSystem_OneOrMoreOfTheInformationIsEmpty(){
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Registration Successful", response.getMessage());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("Elijah");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Login successful", login.getMessage());

        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setBatchNumber("EMP2026001");
        addDrugRequest.setBrandName("Emzor Paracetamol");
        addDrugRequest.setDosage("Tablet");
        addDrugRequest.setStrength("");
        addDrugRequest.setQuantityInStock(100);
        addDrugRequest.setPrice(500);
        addDrugRequest.setId(101);
        addDrugRequest.setGenericName("paracetamol");
        addDrugRequest.setManufacturer("Emzor");
        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
        ChemistDrugManagementService service = new ChemistDrugManagementServiceImpl(drugRepository);
        assertThrows(FillInEveryInformationException.class, () ->  service.addDrug(addDrugRequest));
    }

    @Test
    public void chemistIsLoggedIn_chemistAddsDrugToTheSystem_OneOfTheInformationNeedsToBeUpdated(){
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Registration Successful", response.getMessage());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("Elijah");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Login successful", login.getMessage());

        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setBatchNumber("EMP2026001");
        addDrugRequest.setBrandName("Emzor Paracetamol");
        addDrugRequest.setDosage("Tablet");
        addDrugRequest.setStrength("500mg");
        addDrugRequest.setQuantityInStock(100);
        addDrugRequest.setPrice(500);
        addDrugRequest.setId(101);
        addDrugRequest.setGenericName("paracetamol");
        addDrugRequest.setManufacturer("Emzor");
        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
        ChemistDrugManagementService service = new ChemistDrugManagementServiceImpl(drugRepository);
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setBatchNumber("EMP2026001");
        updateDrugRequest.setBrandName("Emzor Paracetamol");
        updateDrugRequest.setDosage("Tablet");
        updateDrugRequest.setStrength("500mg");
        updateDrugRequest.setQuantityInStock(100);
        updateDrugRequest.setPrice(500);
        updateDrugRequest.setId(101);
        updateDrugRequest.setGenericName("paracetamol");
        updateDrugRequest.setManufacturer("EmzorParacetamol");
        updateDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
        updateDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
        UpdateDrugResponse updateDrugResponse = service.updateDrug(updateDrugRequest);
        assertEquals("Drug information updated successfully", updateDrugResponse.getMessage());
    }

    @Test
    public void chemistIsLoggedIn_chemistUpdataDrugThatIsNotInTheSystem(){
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Registration Successful", response.getMessage());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("Elijah");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Login successful", login.getMessage());

        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setBatchNumber("EMP2026001");
        addDrugRequest.setBrandName("Emzor Paracetamol");
        addDrugRequest.setDosage("Tablet");
        addDrugRequest.setStrength("500mg");
        addDrugRequest.setQuantityInStock(100);
        addDrugRequest.setPrice(500);
        addDrugRequest.setId(101);
        addDrugRequest.setGenericName("paracetamol");
        addDrugRequest.setManufacturer("Emzor");
        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
        ChemistDrugManagementService service = new ChemistDrugManagementServiceImpl(drugRepository);
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setBatchNumber("EMP2026001");
        updateDrugRequest.setBrandName("Emzor Paracetamol");
        updateDrugRequest.setDosage("Tablet");
        updateDrugRequest.setStrength("500mg");
        updateDrugRequest.setQuantityInStock(100);
        updateDrugRequest.setPrice(500);
        updateDrugRequest.setId(100);
        updateDrugRequest.setGenericName("paracetamol");
        updateDrugRequest.setManufacturer("EmzorParacetamol");
        updateDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
        updateDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
        assertThrows(DrugDoesNotExistException.class, () -> service.updateDrug(updateDrugRequest));
    }

    @Test
    public void chemistIsLoggedIn_ChemistSearchesForDrug(){
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Registration Successful", response.getMessage());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("Elijah");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Login successful", login.getMessage());

        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setBatchNumber("EMP2026001");
        addDrugRequest.setBrandName("Emzor Paracetamol");
        addDrugRequest.setDosage("Tablet");
        addDrugRequest.setStrength("500mg");
        addDrugRequest.setQuantityInStock(100);
        addDrugRequest.setPrice(500);
        addDrugRequest.setId(101);
        addDrugRequest.setGenericName("paracetamol");
        addDrugRequest.setManufacturer("Emzor");
        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
        ChemistDrugManagementService service = new ChemistDrugManagementServiceImpl(drugRepository);
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        SearchDrugRequest searchDrugRequest = new SearchDrugRequest();
        searchDrugRequest.setDrugName("Emzor Paracetamol");
        SearchDrugResponse searchDrugResponse = service.searchDrug(searchDrugRequest);
        assertEquals("paracetamol 500mg tablet", searchDrugResponse.getMessage());
    }

    @Test
    public void chemistIsLoggedIn_ChemistSearchesForDrugUsingIncompleteDrugName(){
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Registration Successful", response.getMessage());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("Elijah");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Login successful", login.getMessage());

        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setBatchNumber("EMP2026001");
        addDrugRequest.setBrandName("Emzor Paracetamol");
        addDrugRequest.setDosage("Tablet");
        addDrugRequest.setStrength("500mg");
        addDrugRequest.setQuantityInStock(100);
        addDrugRequest.setPrice(500);
        addDrugRequest.setId(101);
        addDrugRequest.setGenericName("paracetamol");
        addDrugRequest.setManufacturer("Emzor");
        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
        ChemistDrugManagementService service = new ChemistDrugManagementServiceImpl(drugRepository);
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        SearchDrugRequest searchDrugRequest = new SearchDrugRequest();
        searchDrugRequest.setDrugName("para");
        SearchDrugResponse searchDrugResponse = service.searchDrug(searchDrugRequest);
        assertEquals("paracetamol 500mg tablet", searchDrugResponse.getMessage());
    }

    @Test
    public void chemistIsLoggedIn_ChemistDeletesDrug(){
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Registration Successful", response.getMessage());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("Elijah");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Login successful", login.getMessage());

        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setBatchNumber("EMP2026001");
        addDrugRequest.setBrandName("Emzor Paracetamol");
        addDrugRequest.setDosage("Tablet");
        addDrugRequest.setStrength("500mg");
        addDrugRequest.setQuantityInStock(100);
        addDrugRequest.setPrice(500);
        addDrugRequest.setId(101);
        addDrugRequest.setGenericName("paracetamol");
        addDrugRequest.setManufacturer("Emzor");
        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
        ChemistDrugManagementService service = new ChemistDrugManagementServiceImpl(drugRepository);
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        DeleteDrugRequest deleteDrugRequest = new DeleteDrugRequest();
        deleteDrugRequest.setDrugId(101);
        DeleteDrugResponse deleteDrugResponse = service.deleteDrug(deleteDrugRequest);
        assertEquals("Drug deleted successfully", deleteDrugResponse.getMessage());
    }

    @Test
    public void chemistIsLoggedIn_ChemistDeletesDrugThatIsNotInTheSystem(){
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Registration Successful", response.getMessage());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("Elijah");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Login successful", login.getMessage());

        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setBatchNumber("EMP2026001");
        addDrugRequest.setBrandName("Emzor Paracetamol");
        addDrugRequest.setDosage("Tablet");
        addDrugRequest.setStrength("500mg");
        addDrugRequest.setQuantityInStock(100);
        addDrugRequest.setPrice(500);
        addDrugRequest.setId(101);
        addDrugRequest.setGenericName("paracetamol");
        addDrugRequest.setManufacturer("Emzor");
        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
        ChemistDrugManagementService service = new ChemistDrugManagementServiceImpl(drugRepository);
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        DeleteDrugRequest deleteDrugRequest = new DeleteDrugRequest();
        deleteDrugRequest.setDrugId(100);
        assertThrows(DrugDoesNotExistException.class, () -> service.deleteDrug(deleteDrugRequest));
    }

    @Test
    public void chemistIsLoggedIn_ChemistDeletesAllDrugInTheSystem(){
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Registration Successful", response.getMessage());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("Elijah");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Login successful", login.getMessage());

        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setBatchNumber("EMP2026001");
        addDrugRequest.setBrandName("Emzor Paracetamol");
        addDrugRequest.setDosage("Tablet");
        addDrugRequest.setStrength("500mg");
        addDrugRequest.setQuantityInStock(100);
        addDrugRequest.setPrice(500);
        addDrugRequest.setId(101);
        addDrugRequest.setGenericName("paracetamol");
        addDrugRequest.setManufacturer("Emzor");
        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
        ChemistDrugManagementService service = new ChemistDrugManagementServiceImpl(drugRepository);
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        DeleteAllDrugRequest deleteAllDrugRequest = new DeleteAllDrugRequest();
        deleteAllDrugRequest.deleteAllSwitch(true);
        DeleteAllDrugResponse deleteAllDrugResponse = service.deleteAllDrug(deleteAllDrugRequest);
        assertEquals("Drug deleted successfully", deleteAllDrugResponse.getMessage());
    }

    @Test
    public void chemistIsLoggedIn_ChemistChecksForTheListOfDrugsInTheSystem(){
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Registration Successful", response.getMessage());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("Elijah");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Login successful", login.getMessage());

        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setBatchNumber("EMP2026001");
        addDrugRequest.setBrandName("Emzor Paracetamol");
        addDrugRequest.setDosage("Tablet");
        addDrugRequest.setStrength("500mg");
        addDrugRequest.setQuantityInStock(100);
        addDrugRequest.setPrice(500);
        addDrugRequest.setId(101);
        addDrugRequest.setGenericName("paracetamol");
        addDrugRequest.setManufacturer("Emzor");
        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
        ChemistDrugManagementService service = new ChemistDrugManagementServiceImpl(drugRepository);
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        GetAmountOfDrugsRequest getAmountOfDrugsRequest = new GetAmountOfDrugsRequest();
        getAmountOfDrugsRequest.setRequestSwitch(true);
        GetAmountOfDrugsResponse getAmountOfDrugsResponse = service.getAmountOfDrugs(getAmountOfDrugsRequest);
        assertEquals("The Amount Of Drugs is 1", getAmountOfDrugsResponse.getMessage());
    }

//    @Test
//    public void chemistIsLoggedIn_ChemistSearchesForDrugUsingIncompleteDrugName_SystemReturnsAllDrugs(){
//        AuthServiceImpl authentication = new AuthServiceImpl();
//        RegisterUserRequest user = new RegisterUserRequest();
//        user.setUserName("Elijah");
//        user.setFullName("Elijah Miracle");
//        user.setPassWord("BIGDuke004");
//        RegisterUserResponse response = authentication.register(user);
//        assertEquals("Registration Successful", response.getMessage());
//
//        LoginUserRequest userCredentials  = new LoginUserRequest();
//        userCredentials.setUserName("Elijah");
//        userCredentials.setPassword("BIGDuke004");
//        LoginUserResponse login = authentication.login(userCredentials);
//        assertEquals("Login successful", login.getMessage());
//
//        AddDrugRequest addDrugRequest = new AddDrugRequest();
//        addDrugRequest.setBatchNumber("EMP2026001");
//        addDrugRequest.setBrandName("Emzor Paracetamol");
//        addDrugRequest.setDosage("Tablet");
//        addDrugRequest.setStrength("500mg");
//        addDrugRequest.setQuantityInStock(100);
//        addDrugRequest.setPrice(500);
//        addDrugRequest.setId(101);
//        addDrugRequest.setGenericName("paracetamol");
//        addDrugRequest.setManufacturer("Emzor");
//        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
//        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
//        ChemistDrugManagementService service = new ChemistDrugManagementServiceImpl(drugRepository);
//        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
//        assertEquals("Drug added successfully", drugResponse.getMessage());        AddDrugRequest addDrugRequest = new AddDrugRequest();
//
//        AddDrugRequest addDrug = new AddDrugRequest();
//        addDrug.setBatchNumber("EMP2026001");
//        addDrug.setBrandName("Emzor Paracetamol");
//        addDrug.setDosage("Tablet");
//        addDrug.setStrength("500mg");
//        addDrug.setQuantityInStock(100);
//        addDrug.setPrice(500);
//        addDrug.setId(101);
//        addDrug.setGenericName("paracetamol");
//        addDrug.setManufacturer("Emzor");
//        addDrug.setManufactureDate(LocalDate.of(2026,1,10));
//        addDrug.setExpiryDate(LocalDate.of(2028, 1, 10));
//        ChemistDrugManagementService serviceTwo = new ChemistDrugManagementServiceImpl(drugRepository);
//        AddDrugResponse drugResponseTwo = serviceTwo.addDrug(addDrugRequest);
//        assertEquals("Drug added successfully", drugResponseTwo.getMessage());
//
//        ArrayList <Drug> drug = new ArrayList<>();
//        drug.add()
//
//        SearchDrugRequest searchDrugRequest = new SearchDrugRequest();
//        searchDrugRequest.setDrugName("para");
//        SearchDrugResponse searchDrugResponse = service.searchDrug(searchDrugRequest);
//
//    }

    @Test
    public void chemistIsLoggedIn_ChemistChecksForDrugDetails(){
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Registration Successful", response.getMessage());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("Elijah");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Login successful", login.getMessage());

        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setBatchNumber("EMP2026001");
        addDrugRequest.setBrandName("Emzor Paracetamol");
        addDrugRequest.setDosage("Tablet");
        addDrugRequest.setStrength("500mg");
        addDrugRequest.setQuantityInStock(100);
        addDrugRequest.setPrice(500);
        addDrugRequest.setId(101);
        addDrugRequest.setGenericName("paracetamol");
        addDrugRequest.setManufacturer("Emzor");
        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
        ChemistDrugManagementService service = new ChemistDrugManagementServiceImpl(drugRepository);
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        ViewDrugDetailRequest viewDrugDetailRequest = new ViewDrugDetailRequest();
        viewDrugDetailRequest.setBrandName("Emzor Paracetamol");
        ViewDrugDetailResponse drugDetailResponse = service.viewDrugDetail(viewDrugDetailRequest);

        String actual = drugDetailResponse.getMessage();

        assertTrue(actual.contains("Medicine ID: 101"));
        assertTrue(actual.contains("Brand Name: emzor paracetamol"));
        assertTrue(actual.contains("Generic Name: paracetamol"));
        assertTrue(actual.contains("Dosage Form: tablet"));
        assertTrue(actual.contains("Unit Price: 500"));
        assertTrue(actual.contains("Quantity in Stock: 100"));

    }

}
