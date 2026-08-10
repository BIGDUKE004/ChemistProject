package ng.Chemist.service;

import ng.Chemist.Data.model.User;
import ng.Chemist.Data.repositories.UserRepository;
import ng.Chemist.Data.repositories.UserRepositoryImpl;
import ng.Chemist.dtos.request.authServiceRequest.LogOutRequest;
import ng.Chemist.dtos.request.authServiceRequest.LoginUserRequest;
import ng.Chemist.dtos.request.authServiceRequest.RegisterUserRequest;
import ng.Chemist.dtos.response.authServiceResponse.LoginUserResponse;
import ng.Chemist.dtos.response.authServiceResponse.LogoutUserResponse;
import ng.Chemist.dtos.response.authServiceResponse.RegisterUserResponse;
import ng.Chemist.exceptions.AuthServiceExceptions.*;
import ng.Chemist.util.authServiceUtil.AccountName;
import ng.Chemist.util.authServiceUtil.Mapper;

import static ng.Chemist.util.authServiceUtil.Password.checkForDigit;
import static ng.Chemist.util.authServiceUtil.Password.checkForUpperCase;

public class AuthServiceImpl {
    private UserRepository userRepository ;

    public  AuthServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public RegisterUserResponse register (RegisterUserRequest request){
        RegisterUserResponse response = new RegisterUserResponse();
        User user = Mapper.mapToUser(request);
        if(AccountName.checkIfItIsBlank(user.getUserName(), user.getFullName()) == true){
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
        userRepository.save(user);

        response.setMessage("Registration Successful");
        return response;
    }

    public LoginUserResponse login (LoginUserRequest request){
        LoginUserResponse response = new LoginUserResponse();
        User user = userRepository.findByName(request.getUserName());
        if(user == null){
            throw new AccountNotFoundException("Invalid Username or Password");
        }
        if(!user.getPassWord().equals(request.getPassword())){
            throw new WrongPasswordException("Invalid Username or Password");
        }
        user.setLoggedIn(true);
        response.setMessage("Login successful");
        return response;
    }

    public LogoutUserResponse logout(LogOutRequest request) {
        LogoutUserResponse reponse = new LogoutUserResponse();
        User user = userRepository.findByName(request.getUserName());
        if(user == null ) {
            throw new AccountNotFoundException("Account not found");
        }
        user.setLoggedIn(false);
        reponse.setMessage("Logout successful");
        return reponse;
    }
}
