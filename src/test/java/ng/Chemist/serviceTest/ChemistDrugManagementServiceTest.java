package ng.Chemist.serviceTest;
import org.junit.jupiter.api.BeforeEach;
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
    private static final String STORE_ID = "test-store";

    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthServiceImpl authentication;
    @Autowired
    private ChemistDrugManagementServiceImpl service;

    @BeforeEach
    public void setUp(){
        userRepository.deleteAll();
        drugRepository.deleteAll();
    }

    @Test
    public void chemistIsLoggedIn_chemistAddsDrugToTheSystem(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BIGDuke004");
        user.setStoreName("Test Pharmacy");
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
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest, STORE_ID);
        assertEquals("Drug added successfully", drugResponse.getMessage());
    }

    @Test
    public void chemistIsLoggedIn_chemistAddsDrugToTheSystem_OneOrMoreOfTheInformationIsEmpty(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Eli");
        user.setFullName("Elijah bobo");
        user.setPassWord("BIGDuke004");
        user.setStoreName("Test Pharmacy");
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
        assertThrows(FillInEveryInformationException.class, () ->  service.addDrug(addDrugRequest, STORE_ID));
    }

    @Test
    public void chemistIsLoggedIn_chemistAddsDrugToTheSystem_OneOfTheInformationNeedsToBeUpdated(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("duke");
        user.setFullName("Elijah duke");
        user.setPassWord("BIGDuke004");
        user.setStoreName("Test Pharmacy");
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
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest, STORE_ID);
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
        UpdateDrugResponse updateDrugResponse = service.updateDrug(updateDrugRequest, STORE_ID);
        assertEquals("Drug information updated successfully", updateDrugResponse.getMessage());
    }

    @Test
    public void chemistIsLoggedIn_chemistUpdataDrugThatIsNotInTheSystem(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("nNN");
        user.setFullName("Elijah fx");
        user.setPassWord("BIGDuke004");
        user.setStoreName("Test Pharmacy");
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
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest, STORE_ID);
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
        assertThrows(DrugDoesNotExistException.class, () -> service.updateDrug(updateDrugRequest, STORE_ID));
    }

    @Test
    public void chemistIsLoggedIn_ChemistSearchesForDrug(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("ddgGGd");
        user.setFullName("Elijah M");
        user.setPassWord("BIGDuke004");
        user.setStoreName("Test Pharmacy");
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
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest, STORE_ID);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        SearchDrugRequest searchDrugRequest = new SearchDrugRequest();
        searchDrugRequest.setGenericName("blood tonic");
        SearchDrugResponse searchDrugResponse = service.searchDrug(searchDrugRequest, STORE_ID);
        assertEquals(1, searchDrugResponse.getDrugs().size());
    }

    @Test
    public void chemistIsLoggedIn_ChemistDeletesDrugThatIsNotInTheSystem(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("jah");
        user.setFullName("Elijah miracle");
        user.setPassWord("BIGDuke004");
        user.setStoreName("Test Pharmacy");
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
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest, STORE_ID);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        DeleteDrugRequest deleteDrugRequest = new DeleteDrugRequest();
        deleteDrugRequest.setBrandName("DoesNotExist");
        assertThrows(DrugDoesNotExistException.class, () -> service.deleteDrug(deleteDrugRequest, STORE_ID));
    }

    @Test
    public void chemistIsLoggedIn_ChemistDeletesAllDrugInTheSystem(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("vn");
        user.setFullName("Elijah mmm");
        user.setPassWord("BIGDuke004");
        user.setStoreName("Test Pharmacy");
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
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest, STORE_ID);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        DeleteAllDrugRequest deleteAllDrugRequest = new DeleteAllDrugRequest();
        deleteAllDrugRequest.setOption("yes");
        DeleteAllDrugResponse deleteAllDrugResponse = service.deleteAllDrug(deleteAllDrugRequest, STORE_ID);
        assertEquals("Drug deleted successfully", deleteAllDrugResponse.getMessage());
    }

    @Test
    public void chemistIsLoggedIn_ChemistChecksForTheListOfDrugsInTheSystem(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("vvs");
        user.setFullName("Elijah vvv");
        user.setPassWord("BIGDuke004");
        user.setStoreName("Test Pharmacy");
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
        AddDrugResponse drugResponse = service.addDrug(addDrugRequest, STORE_ID);
        assertEquals("Drug added successfully", drugResponse.getMessage());

        GetAmountOfDrugsResponse getAmountOfDrugsResponse = service.getAmountOfDrugs(STORE_ID);
        assertTrue(getAmountOfDrugsResponse.getMessage().startsWith("The Amount Of Drugs is"));
    }
}