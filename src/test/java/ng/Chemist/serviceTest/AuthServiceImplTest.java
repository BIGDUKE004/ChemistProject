package ng.Chemist.serviceTest;

import ng.Chemist.dtos.request.authServiceRequest.LogOutRequest;
import ng.Chemist.dtos.request.authServiceRequest.LoginUserRequest;
import ng.Chemist.dtos.request.authServiceRequest.RegisterUserRequest;
import ng.Chemist.Data.repositories.UserRepository;
import ng.Chemist.dtos.response.authServiceResponse.LoginUserResponse;
import ng.Chemist.dtos.response.authServiceResponse.LogoutUserResponse;
import ng.Chemist.dtos.response.authServiceResponse.RegisterUserResponse;
import ng.Chemist.exceptions.AuthServiceExceptions.*;
import ng.Chemist.service.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthServiceImplTest {

    @Autowired
    private AuthServiceImpl authentication;
    @Autowired
    private UserRepository userRepository;
    @BeforeEach
    public void setUp(){
    userRepository.deleteAll();
    }

    @Test
    public void testThatUserRegistersAnAccount_AccountCountIsOne(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BIGDuke004");
        user.setStoreName("Test Pharmacy");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Elijah Miracle", response.getFullName());
    }

    @Test
    public void testThatUserRegistersWithAnEmptyUserName_UserGetInvalidUserNameException(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName(" ");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BIGDuke004");
        assertThrows(InvalidUserNameException.class, () -> authentication.register(user));
    }

    @Test
    public void testThatUserRegistersWithAnEmptyFullName_UserGetInvalidUserNameException(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName(" ");
        user.setPassWord("BIGDuke004");
        assertThrows(InvalidUserNameException.class, () -> authentication.register(user));
    }

    @Test
    public void testThatUserRegistersWithAPassWordThatIsNotUpToEightCharacters_UserGetInvalidPasswordException(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("big");
        assertThrows(InvalidPasswordLengthException.class, () -> authentication.register(user));
    }

    @Test
    public void testThatUserRegistersWithAPassWordDoesNotContainAnUpperCaseCharacter_UserGetInvalidPasswordException(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("bigduke004");
        assertThrows(InvalidCharacterCaseException.class, () -> authentication.register(user));
    }

    @Test
    public void testThatUserRegistersWithAPassWordDoesNotContainADigit_UserGetInvalidPasswordException(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BigdukeMiracle");
        assertThrows(NoDigitIncludedException.class, () -> authentication.register(user));
    }

    @Test
    public void testThatIHaveAnAccountRegistered_UserLogsIn(){
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
    }

    @Test
    public void testThatIHaveAnAccountRegistered_UserLogsInAnAccountThatDoesNotExist(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BIGDuke004");
        user.setStoreName("Test Pharmacy");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Elijah Miracle", response.getFullName());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("Benjamin");
        userCredentials.setPassword("BIGDuke004");
        assertThrows(AccountNotFoundException.class, () -> authentication.login(userCredentials));
    }

    @Test
    public void testThatIHaveAnAccountRegistered_UserLogsInWithAWrongPassword(){
        RegisterUserRequest user = new RegisterUserRequest();
        user.setUserName("Elijah");
        user.setFullName("Elijah Miracle");
        user.setPassWord("BIGDuke004");
        user.setStoreName("Test Pharmacy");
        RegisterUserResponse response = authentication.register(user);
        assertEquals("Elijah Miracle", response.getFullName());

        LoginUserRequest userCredentials  = new LoginUserRequest();
        userCredentials.setUserName("Elijah");
        userCredentials.setPassword("BIGDuke");
        assertThrows(WrongPasswordException.class, () -> authentication.login(userCredentials));
    }

    @Test
    public void testThatIHaveAnAccountRegistered_IAmLoggedIn_UserLogsOut(){
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

        LogOutRequest request = new LogOutRequest();
        request.setUserName("Elijah");
        LogoutUserResponse log0utResponse = authentication.logout(request);
        assertFalse(log0utResponse.isLoggedIn());
    }
}