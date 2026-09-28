package Java.ng.Chemist.service;

import Java.ng.Chemist.Data.model.User;
import Java.ng.Chemist.Data.repositories.UserRepository;
import Java.ng.Chemist.dtos.request.authServiceRequest.LogOutRequest;
import Java.ng.Chemist.dtos.request.authServiceRequest.LoginUserRequest;
import Java.ng.Chemist.dtos.request.authServiceRequest.RegisterUserRequest;
import Java.ng.Chemist.dtos.response.authServiceResponse.LoginUserResponse;
import Java.ng.Chemist.dtos.response.authServiceResponse.LogoutUserResponse;
import Java.ng.Chemist.dtos.response.authServiceResponse.RegisterUserResponse;
import Java.ng.Chemist.exceptions.AuthServiceExceptions.*;
import ng.Chemist.exceptions.AuthServiceExceptions.*;
import Java.ng.Chemist.security.JwtService;
import Java.ng.Chemist.util.authServiceUtil.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static Java.ng.Chemist.util.authServiceUtil.Password.checkForDigit;
import static Java.ng.Chemist.util.authServiceUtil.Password.checkForUpperCase;
@Service
public class AuthServiceImpl {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository ;

    public RegisterUserResponse register (RegisterUserRequest request){
        User user = Mapper.mapToUser(request);
        if(userRepository.findByUserName(request.getUserName()).isPresent() == true){
            throw new InvalidUserNameException("User Name Already Exist");
        }
        if(user.getUserName().isBlank() == true || user.getPassWord().isBlank() == true || user.getFullName().isBlank() == true){
            throw new InvalidUserNameException("Invalid Name");
        }
        if(user.getPassWord().length() < 8){
            throw new InvalidPasswordLengthException("Password Must Be More Than 8 characters");
        }
        if(checkForUpperCase(user.getPassWord()) == false){
            throw new InvalidCharacterCaseException("Password Must Contain Atleast One UpperCase Character");
        }
        if(checkForDigit(user.getPassWord()) == false){
            throw new NoDigitIncludedException("Password Must Contain Digit");
        }
        String hashed = passwordEncoder.encode(user.getPassWord());
        user.setPassWord(hashed);
        User savedUser = userRepository.save(user);

        RegisterUserResponse response = new RegisterUserResponse();
        response.setFullName(savedUser.getFullName());
        response.setUserId(savedUser.getId());
        response.setUserName(savedUser.getUserName());
        response.setLoggedIn(savedUser.isLoggedIn());
        return response;
    }

    public LoginUserResponse login (LoginUserRequest request){
        LoginUserResponse response = new LoginUserResponse();
        Optional<User> user = userRepository.findByUserName(request.getUserName());
        if(user.isEmpty()){
            throw new AccountNotFoundException("Invalid Username or Password");
        }
        if(!passwordEncoder.matches(request.getPassword(), user.get().getPassWord())){
            throw new WrongPasswordException("Invalid Username or Password");
        }
        user.get().setLoggedIn(true);
        userRepository.save(user.get());

        User savedUser = user.get();

        response.setUserName(user.get().getUserName());
        response.setFullName(user.get().getFullName());
        response.setJwtId(jwtService.createJwt(savedUser));

        return response;
    }

    public LogoutUserResponse logout(LogOutRequest request) {
        LogoutUserResponse response = new LogoutUserResponse();
        Optional<User> user = userRepository.findByUserName(request.getUserName());
        if(user.isEmpty()) {
            throw new AccountNotFoundException("Account not found");
        }
        user.get().setLoggedIn(false);

        User savedUser = userRepository.save(user.get());

        response.setLoggedIn(savedUser.isLoggedIn());
        return response;
    }
}
