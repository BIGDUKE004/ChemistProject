//package ng.chemist.serviceTest;
//
//import ng.Chemist.Data.model.DispenseDrug;
//import ng.Chemist.Data.repositories.DrugRepository;
//import ng.Chemist.Data.repositories.UserRepository;
//import ng.Chemist.dtos.request.authServiceRequest.LoginUserRequest;
//import ng.Chemist.dtos.request.authServiceRequest.RegisterUserRequest;
//import ng.Chemist.dtos.request.chemistDrugManagementServiceRequest.AddDrugRequest;
//import ng.Chemist.dtos.request.chemistSalesServiceRequest.sellDrugRequest;
//import ng.Chemist.dtos.response.authServiceResponse.LoginUserResponse;
//import ng.Chemist.dtos.response.authServiceResponse.RegisterUserResponse;
//import ng.Chemist.dtos.response.chemistDrugManagementServiceResponse.AddDrugResponse;
//import ng.Chemist.dtos.response.chemistSalesServiceResponse.sellDrugResponse;
//import ng.Chemist.exceptions.ChemistSalesManagementExceptions.DrugNotFoundException;
//import ng.Chemist.exceptions.ChemistSalesManagementExceptions.UserNotLoggedInException;
//import ng.Chemist.service.*;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//@SpringBootApplication
//public class ChemistSalesServiceTest {
//    @Test
//    public void chemistIsLoggedIn_ChemistAddsDrugsToTheSystem_ChemistDispenseADrug(){
//        UserRepository userRepository = new UserRepository();
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
//        DrugRepository drugRepository = new DrugRepository();
//
//        AddDrugRequest addDrugRequest = new AddDrugRequest();
//        addDrugRequest.setBatchNumber("EMP2026001");
//        addDrugRequest.setBrandName("Emzor Paracetamol");
//        addDrugRequest.setDosage("Tablet");
//        addDrugRequest.setStrength("500mg");
//        addDrugRequest.setQuantityInStock(100);
//        addDrugRequest.setPrice(BigDecimal.valueOf(500));
//        addDrugRequest.setId(101);
//        addDrugRequest.setGenericName("paracetamol");
//        addDrugRequest.setManufacturer("Emzor");
//        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
//        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
//        ChemistDrugManagementService service = new ChemistDrugManagementServiceImpl(drugRepository);
//        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
//        assertEquals("Drug added successfully", drugResponse.getMessage());
//
//        DispenseDrug dispenseDrug = new DispenseDrug();
//        dispenseDrug.setBatchId("2026");
//        dispenseDrug.setId(101);
//        dispenseDrug.setDrugName("Emzor Paracetamol");
//        dispenseDrug.setDosage("Tablet");
//        dispenseDrug.setQuantity(5);
//
//        sellDrugRequest sellRequest = new sellDrugRequest();
//        sellRequest.setDrugs(dispenseDrug);
//        sellRequest.setName("Elijah");
//
//        ChemistSalesServiceImpl salesService = new ChemistSalesServiceImpl(drugRepository, userRepository);
//
//        sellDrugResponse salesResponse = salesService.sellDrug(sellRequest);
//
//        assertEquals(
//                "Total Amount: ₦2500\n" +
//                        "Quantity Sold: 5\n" +
//                        "Drugs: [5 x Emzor Paracetamol x Tablet = 2500]",
//                salesResponse.messageToString()
//        );
//    }
//
//@Test
//    public void chemistIsLoggedIn_ChemistAddsDrugsToTheSystem_ChemistDispenseMoreThanOneDrug(){
//    UserRepositoryImpl userRepository = new UserRepositoryImpl();
//    AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
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
//        DrugRepository drugRepository = new DrugRepositoryImpl();
//
//        AddDrugRequest addDrugRequest = new AddDrugRequest();
//        addDrugRequest.setBatchNumber("EMP2026001");
//        addDrugRequest.setBrandName("Emzor Paracetamol");
//        addDrugRequest.setDosage("Tablet");
//        addDrugRequest.setStrength("500mg");
//        addDrugRequest.setQuantityInStock(100);
//        addDrugRequest.setPrice(BigDecimal.valueOf(500));
//        addDrugRequest.setId(101);
//        addDrugRequest.setGenericName("paracetamol");
//        addDrugRequest.setManufacturer("Emzor");
//        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
//        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
//        ChemistDrugManagementService service = new ChemistDrugManagementServiceImpl(drugRepository);
//        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
//        assertEquals("Drug added successfully", drugResponse.getMessage());
//
//        AddDrugRequest addAnotherDrugRequest = new AddDrugRequest();
//        addAnotherDrugRequest.setBatchNumber("EMP2027001");
//        addAnotherDrugRequest.setBrandName("vitamin");
//        addAnotherDrugRequest.setDosage("Tonic");
//        addAnotherDrugRequest.setStrength("250mg");
//        addAnotherDrugRequest.setQuantityInStock(50);
//        addAnotherDrugRequest.setPrice(BigDecimal.valueOf(1500));
//        addAnotherDrugRequest.setId(190);
//        addAnotherDrugRequest.setGenericName("vitamin");
//        addAnotherDrugRequest.setManufacturer("shukurat");
//        addAnotherDrugRequest.setManufactureDate(LocalDate.of(2023,8,10));
//        addAnotherDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
//        AddDrugResponse secondDrugResponse = service.addDrug(addAnotherDrugRequest);
//        assertEquals("Drug added successfully", secondDrugResponse.getMessage());
//
//        DispenseDrug dispenseDrug = new DispenseDrug();
//        dispenseDrug.setBatchId("2026");
//        dispenseDrug.setId(101);
//        dispenseDrug.setDosage("Tablet");
//        dispenseDrug.setDrugName("Emzor Paracetamol");
//        dispenseDrug.setQuantity(5);
//
//        DispenseDrug dispenseDrugTwo = new DispenseDrug();
//        dispenseDrugTwo.setBatchId("2026");
//        dispenseDrugTwo.setId(190);
//        dispenseDrugTwo.setDosage("Tonic");
//        dispenseDrugTwo.setDrugName("vitamin");
//        dispenseDrugTwo.setQuantity(5);
//
//
//        sellDrugRequest sellRequest = new sellDrugRequest();
//        sellRequest.setDrugs(dispenseDrug);
//        sellRequest.setDrugs(dispenseDrugTwo);
//        sellRequest.setName("Elijah");
//
//        ChemistSalesServiceImpl salesService = new ChemistSalesServiceImpl(drugRepository, userRepository);
//
//        sellDrugResponse salesResponse = salesService.sellDrug(sellRequest);
//        assertEquals(
//                "Total Amount: ₦10000\n" +
//                        "Quantity Sold: 10\n" +
//                        "Drugs: [5 x Emzor Paracetamol x Tablet = 2500, 5 x vitamin x Tonic = 7500]",
//                salesResponse.messageToString()
//        );
//    }
//
//    @Test
//    public void chemistIsLoggedIn_ChemistAddsDrugsToTheSystem_ChemistDispenseDrugThatDoesNotExist(){
//    UserRepositoryImpl userRepository = new UserRepositoryImpl();
//    AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
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
//        DrugRepository drugRepository = new DrugRepositoryImpl();
//
//        AddDrugRequest addDrugRequest = new AddDrugRequest();
//        addDrugRequest.setBatchNumber("EMP2026001");
//        addDrugRequest.setBrandName("Emzor Paracetamol");
//        addDrugRequest.setDosage("Tablet");
//        addDrugRequest.setStrength("500mg");
//        addDrugRequest.setQuantityInStock(100);
//        addDrugRequest.setPrice(BigDecimal.valueOf(500));
//        addDrugRequest.setId(101);
//        addDrugRequest.setGenericName("paracetamol");
//        addDrugRequest.setManufacturer("Emzor");
//        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
//        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
//        ChemistDrugManagementService service = new ChemistDrugManagementServiceImpl(drugRepository);
//        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
//        assertEquals("Drug added successfully", drugResponse.getMessage());
//
//        AddDrugRequest addAnotherDrugRequest = new AddDrugRequest();
//        addAnotherDrugRequest.setBatchNumber("EMP2027001");
//        addAnotherDrugRequest.setBrandName("vitamin");
//        addAnotherDrugRequest.setDosage("Tonic");
//        addAnotherDrugRequest.setStrength("250mg");
//        addAnotherDrugRequest.setQuantityInStock(50);
//        addAnotherDrugRequest.setPrice(BigDecimal.valueOf(1500));
//        addAnotherDrugRequest.setId(190);
//        addAnotherDrugRequest.setGenericName("vitamin");
//        addAnotherDrugRequest.setManufacturer("shukurat");
//        addAnotherDrugRequest.setManufactureDate(LocalDate.of(2023,8,10));
//        addAnotherDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
//        AddDrugResponse secondDrugResponse = service.addDrug(addAnotherDrugRequest);
//        assertEquals("Drug added successfully", secondDrugResponse.getMessage());
//
//        DispenseDrug dispenseDrug = new DispenseDrug();
//        dispenseDrug.setBatchId("2026");
//        dispenseDrug.setId(101);
//        dispenseDrug.setDosage("Tablet");
//        dispenseDrug.setDrugName("Emzor Paracetamol");
//        dispenseDrug.setQuantity(5);
//
//        DispenseDrug dispenseDrugTwo = new DispenseDrug();
//        dispenseDrugTwo.setBatchId("2026");
//        dispenseDrugTwo.setId(190);
//        dispenseDrugTwo.setDosage("Tablet");
//        dispenseDrugTwo.setDrugName("vitamin");
//        dispenseDrugTwo.setQuantity(5);
//
//
//        sellDrugRequest sellRequest = new sellDrugRequest();
//        sellRequest.setDrugs(dispenseDrug);
//        sellRequest.setDrugs(dispenseDrugTwo);
//        sellRequest.setName("Elijah");
//
//        ChemistSalesServiceImpl salesService = new ChemistSalesServiceImpl(drugRepository, userRepository);
//
//        assertThrows(DrugNotFoundException.class, () -> salesService.sellDrug(sellRequest));
//
//    }
//
//    @Test
//    public void chemistIsNotLoggedIn_ChemistAddsDrugsToTheSystem_ChemistDispenseDrugThatDoesNotExist(){
//    UserRepositoryImpl userRepository = new UserRepositoryImpl();
//    AuthServiceImpl authentication = new AuthServiceImpl(userRepository);
//        RegisterUserRequest user = new RegisterUserRequest();
//        user.setUserName("Elijah");
//        user.setFullName("Elijah Miracle");
//        user.setPassWord("BIGDuke004");
//        RegisterUserResponse response = authentication.register(user);
//        assertEquals("Registration Successful", response.getMessage());
//
//        DrugRepository drugRepository = new DrugRepositoryImpl();
//
//        AddDrugRequest addDrugRequest = new AddDrugRequest();
//        addDrugRequest.setBatchNumber("EMP2026001");
//        addDrugRequest.setBrandName("Emzor Paracetamol");
//        addDrugRequest.setDosage("Tablet");
//        addDrugRequest.setStrength("500mg");
//        addDrugRequest.setQuantityInStock(100);
//        addDrugRequest.setPrice(BigDecimal.valueOf(500));
//        addDrugRequest.setId(101);
//        addDrugRequest.setGenericName("paracetamol");
//        addDrugRequest.setManufacturer("Emzor");
//        addDrugRequest.setManufactureDate(LocalDate.of(2026,1,10));
//        addDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
//        ChemistDrugManagementService service = new ChemistDrugManagementServiceImpl(drugRepository);
//        AddDrugResponse drugResponse = service.addDrug(addDrugRequest);
//        assertEquals("Drug added successfully", drugResponse.getMessage());
//
//        AddDrugRequest addAnotherDrugRequest = new AddDrugRequest();
//        addAnotherDrugRequest.setBatchNumber("EMP2027001");
//        addAnotherDrugRequest.setBrandName("vitamin");
//        addAnotherDrugRequest.setDosage("Tonic");
//        addAnotherDrugRequest.setStrength("250mg");
//        addAnotherDrugRequest.setQuantityInStock(50);
//        addAnotherDrugRequest.setPrice(BigDecimal.valueOf(1500));
//        addAnotherDrugRequest.setId(190);
//        addAnotherDrugRequest.setGenericName("vitamin");
//        addAnotherDrugRequest.setManufacturer("shukurat");
//        addAnotherDrugRequest.setManufactureDate(LocalDate.of(2023,8,10));
//        addAnotherDrugRequest.setExpiryDate(LocalDate.of(2028, 1, 10));
//        AddDrugResponse secondDrugResponse = service.addDrug(addAnotherDrugRequest);
//        assertEquals("Drug added successfully", secondDrugResponse.getMessage());
//
//        DispenseDrug dispenseDrug = new DispenseDrug();
//        dispenseDrug.setBatchId("2026");
//        dispenseDrug.setId(101);
//        dispenseDrug.setDosage("Tablet");
//        dispenseDrug.setDrugName("Emzor Paracetamol");
//        dispenseDrug.setQuantity(5);
//
//        DispenseDrug dispenseDrugTwo = new DispenseDrug();
//        dispenseDrugTwo.setBatchId("2026");
//        dispenseDrugTwo.setId(190);
//        dispenseDrugTwo.setDosage("Tonic");
//        dispenseDrugTwo.setDrugName("vitamin");
//        dispenseDrugTwo.setQuantity(5);
//
//
//        sellDrugRequest sellRequest = new sellDrugRequest();
//        sellRequest.setDrugs(dispenseDrug);
//        sellRequest.setDrugs(dispenseDrugTwo);
//        sellRequest.setName("Elijah");
//
//        ChemistSalesServiceImpl salesService = new ChemistSalesServiceImpl(drugRepository, userRepository);
//
//        assertThrows(UserNotLoggedInException.class, () -> salesService.sellDrug(sellRequest));
//
//    }
//
//
//}
//
