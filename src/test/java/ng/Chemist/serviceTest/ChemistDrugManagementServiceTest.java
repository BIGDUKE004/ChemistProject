package ng.Chemist.serviceTest;

import ng.Chemist.Data.repositories.DrugRepository;
import ng.Chemist.Data.repositories.UserRepository;
import ng.Chemist.dtos.request.authServiceRequest.LoginUserRequest;
import ng.Chemist.dtos.request.authServiceRequest.RegisterUserRequest;
import ng.Chemist.dtos.request.chemistDrugManagementServiceRequest.*;
import ng.Chemist.dtos.response.chemistDrugManagementServiceResponse.*;
import ng.Chemist.dtos.response.authServiceResponse.LoginUserResponse;
import ng.Chemist.dtos.response.authServiceResponse.RegisterUserResponse;
import ng.Chemist.exceptions.ChemistDrugManagementServiceException.FillInEveryInformationException;
import ng.Chemist.exceptions.repositoriesException.DrugDoesNotExistException;
import ng.Chemist.service.AuthServiceImpl;
import ng.Chemist.service.ChemistDrugManagementServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ChemistDrugManagementServiceTest {
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthServiceImpl authentication;
    @Autowired
    private ChemistDrugManagementServiceImpl service;

    @Test
    public void chemistIsLoggedIn_chemistAddsDrugToTheSystem(){
//        AuthServiceImpl authentication = new AuthServiceImpl(this.userRepository);
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Elijah Miracle", response.getFullName());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("Elijah");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Elijah Miracle", login.getFullName());

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
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
        assertEquals("Drug added successfully", drugResponse.getMessage());
    }

    @Test
    public void chemistIsLoggedIn_chemistAddsDrugToTheSystem_OneOrMoreOfTheInformationIsEmpty(){
//        AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Eli");
        user.setFullName("Elijah bobo");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Elijah bobo", response.getFullName());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("Eli");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Elijah bobo", login.getFullName());

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
        assertThrows(FillInEveryInformationException.class, () ->  service.addDrug(addDrugRequest));
    }

    @Test
    public void chemistIsLoggedIn_chemistAddsDrugToTheSystem_OneOfTheInformationNeedsToBeUpdated(){
//        AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("duke");
        user.setFullName("Elijah duke");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Elijah duke", response.getFullName());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("duke");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Elijah duke", login.getFullName());

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
//        AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("nNN");
        user.setFullName("Elijah fx");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Elijah fx", response.getFullName());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("nNN");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Elijah fx", login.getFullName());

        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setBatchNumber("EMP2026");
        addDrugRequest.setBrandName("Emzor Paracetamol");
        addDrugRequest.setDosage("Tonic");
        addDrugRequest.setStrength("450mg");
        addDrugRequest.setQuantityInStock(100);
        addDrugRequest.setPrice(500);
        addDrugRequest.setId(10);
        addDrugRequest.setGenericName("paracetamol");
        addDrugRequest.setManufacturer("EmzorParacetamol");
        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setBatchNumber("EMP2026");
        updateDrugRequest.setBrandName("Emzor Paracetamol");
        updateDrugRequest.setDosage("Tonic");
        updateDrugRequest.setStrength("450mg");
        updateDrugRequest.setQuantityInStock(100);
        updateDrugRequest.setPrice(500);
        updateDrugRequest.setId(100000);
        updateDrugRequest.setGenericName("paracetamol");
        updateDrugRequest.setManufacturer("EmzorParacetamol");
        updateDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
        updateDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
        assertThrows(DrugDoesNotExistException.class, () -> service.updateDrug(updateDrugRequest));
    }

    @Test
    public void chemistIsLoggedIn_ChemistSearchesForDrug(){
//        AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("ddgGGd");
        user.setFullName("Elijah M");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Elijah M", response.getFullName());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("ddgGGd");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Elijah M", login.getFullName());

        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setBatchNumber("EMP2026001");
        addDrugRequest.setBrandName("Emzor Paracetamol");
        addDrugRequest.setDosage("Tablet");
        addDrugRequest.setStrength("500mg");
        addDrugRequest.setQuantityInStock(100);
        addDrugRequest.setPrice(500);
        addDrugRequest.setId(101);
        addDrugRequest.setGenericName("blood tonic");
        addDrugRequest.setManufacturer("Emzor");
        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        SearchDrugRequest searchDrugRequest = new SearchDrugRequest();
        searchDrugRequest.setGenericName("blood tonic");
        SearchDrugResponse searchDrugResponse = service.searchDrug(searchDrugRequest);
        assertEquals("blood tonic 500mg Tablet", searchDrugResponse.getMessage());
    }

//    @Test
//    public void chemistIsLoggedIn_ChemistSearchesForDrugUsingIncompleteDrugName(){
////        AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
//        RegisterUserRequest user = new RegisterUserRequest();
//        user.setUserName("mmimim");
//        user.setFullName("Elijah mimi");
//        user.setPassWord("BIGDuke004");
//        RegisterUserResponse response = authentication.register(user);
//        assertEquals("Elijah mimi", response.getFullName());
//
//        LoginUserRequest userCredentials  = new LoginUserRequest();
//        userCredentials.setUserName("mmimim");
//        userCredentials.setPassword("BIGDuke004");
//        LoginUserResponse login = authentication.login(userCredentials);
//        assertEquals("Elijah mimi", login.getFullName());
//
//        AddDrugRequest addDrugRequest = new AddDrugRequest();
//        addDrugRequest.setBatchNumber("EMP2026001");
//        addDrugRequest.setBrandName("Paracetamol");
//        addDrugRequest.setDosage("tonic");
//        addDrugRequest.setStrength("1500mg");
//        addDrugRequest.setQuantityInStock(100);
//        addDrugRequest.setPrice(500);
//        addDrugRequest.setId(1010);
//        addDrugRequest.setGenericName("paracetamol");
//        addDrugRequest.setManufacturer("Emzor");
//        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
//        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
//        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
//        assertEquals("Drug added successfully", drugResponse.getMessage());
//
//        SearchDrugRequest searchDrugRequest = new SearchDrugRequest();
//        searchDrugRequest.setGenericName("para");
//        SearchDrugResponse searchDrugResponse = service.searchDrug(searchDrugRequest);
//        assertEquals("paracetamol 500mg tablet", searchDrugResponse.getMessage());
//    }

//    @Test
//    public void chemistIsLoggedIn_ChemistDeletesDrug(){
//        UserRepositoryImpl userRepository = new UserRepositoryImpl();
//        AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
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
//        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
//        assertEquals("Drug added successfully", drugResponse.getMessage());
//
//        DeleteDrugRequest deleteDrugRequest = new DeleteDrugRequest();
//        deleteDrugRequest.setDrugId(101);
//        DeleteDrugResponse deleteDrugResponse = service.deleteDrug(deleteDrugRequest);
//        assertEquals("Drug deleted successfully", deleteDrugResponse.getMessage());
//    }
//
    @Test
    public void chemistIsLoggedIn_ChemistDeletesDrugThatIsNotInTheSystem(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("jah");
        user.setFullName("Elijah miracle");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Elijah miracle", response.getFullName());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("jah");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Elijah miracle", login.getFullName());

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
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        DeleteDrugRequest deleteDrugRequest = new DeleteDrugRequest();
        deleteDrugRequest.setBrandName("DoesNotExist");
        assertThrows(DrugDoesNotExistException.class, () -> service.deleteDrug(deleteDrugRequest));
    }

    @Test
    public void chemistIsLoggedIn_ChemistDeletesAllDrugInTheSystem(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("vn");
        user.setFullName("Elijah mmm");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Elijah mmm", response.getFullName());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("vn");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Elijah mmm", login.getFullName());

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
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        DeleteAllDrugRequest deleteAllDrugRequest = new DeleteAllDrugRequest();
        deleteAllDrugRequest.setOption("yes");
        DeleteAllDrugResponse deleteAllDrugResponse = service.deleteAllDrug(deleteAllDrugRequest);
        assertEquals("Drug deleted successfully", deleteAllDrugResponse.getMessage());
    }

    @Test
    public void chemistIsLoggedIn_ChemistChecksForTheListOfDrugsInTheSystem(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("vvs");
        user.setFullName("Elijah vvv");
        user.setPassWord("BIGDuke004");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Elijah vvv", response.getFullName());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("vvs");
        userCredentials.setPassword("BIGDuke004");
        LoginUserResponse login = authentication.login(userCredentials);
        assertEquals("Elijah vvv", login.getFullName());

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
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        GetAmountOfDrugsRequest getAmountOfDrugsRequest = new GetAmountOfDrugsRequest();
        getAmountOfDrugsRequest.setRequestSwitch(true);
        GetAmountOfDrugsResponse getAmountOfDrugsResponse = service.getAmountOfDrugs();
        assertEquals("The Amount Of Drugs is 3", getAmountOfDrugsResponse.getMessage());
    }

//    @Test
//    public void chemistIsLoggedIn_ChemistSearchesForDrugUsingIncompleteDrugName_SystemReturnsAllDrugs(){
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

//    @Test
//    public void chemistIsLoggedIn_ChemistChecksForDrugDetails(){
//        RegisterUserRequest user = new RegisterUserRequest();
//        user.setUserName("g");
//        user.setFullName("Elijah qwe");
//        user.setPassWord("BIGDuke004");
//        RegisterUserResponse response = authentication.register(user);
//        assertEquals("Elijah qwe", response.getFullName());
//
//        LoginUserRequest userCredentials  = new LoginUserRequest();
//        userCredentials.setUserName("g");
//        userCredentials.setPassword("BIGDuke004");
//        LoginUserResponse login = authentication.login(userCredentials);
//        assertEquals("Elijah qwe", login.getFullName());
//
//        AddDrugRequest addDrugRequest = new AddDrugRequest();
//        addDrugRequest.setBatchNumber("Em");
//        addDrugRequest.setBrandName("panadol");
//        addDrugRequest.setDosage("toxic tonic");
//        addDrugRequest.setStrength("100mg");
//        addDrugRequest.setQuantityInStock(100);
//        addDrugRequest.setPrice(500);
//        addDrugRequest.setId(1078);
//        addDrugRequest.setGenericName("em-panadol");
//        addDrugRequest.setManufacturer("gg");
//        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
//        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
//        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
//        assertEquals("Drug added successfully", drugResponse.getMessage());
//
//        ViewDrugDetailRequest viewDrugDetailRequest = new ViewDrugDetailRequest();
//        viewDrugDetailRequest.setBrandName("panadol");
//        ViewDrugDetailResponse drugDetailResponse = service.viewDrugDetail(viewDrugDetailRequest);
//
//        String actual = drugDetailResponse.getMessage();
//
//        assertTrue(actual.contains("Brand Name: panadol"));
//        assertTrue(actual.contains("Generic Name: em-panadol"));
//        assertTrue(actual.contains("Dosage Form: toxic tonic"));
//        assertTrue(actual.contains("Unit Price: 500"));
//        assertTrue(actual.contains("Quantity in Stock: 100"));
//
//    }

}
