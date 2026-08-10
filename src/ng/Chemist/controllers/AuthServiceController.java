package ng.Chemist.controllers;

import ng.Chemist.Data.repositories.UserRepository;
import ng.Chemist.Data.repositories.UserRepositoryImpl;
import ng.Chemist.dtos.request.authServiceRequest.LogOutRequest;
import ng.Chemist.dtos.request.authServiceRequest.LoginUserRequest;
import ng.Chemist.dtos.request.authServiceRequest.RegisterUserRequest;
import ng.Chemist.dtos.response.authServiceResponse.LoginUserResponse;
import ng.Chemist.dtos.response.authServiceResponse.LogoutUserResponse;
import ng.Chemist.dtos.response.authServiceResponse.RegisterUserResponse;
import ng.Chemist.service.AuthServiceImpl;

public class AuthServiceController {
    private final static UserRepository userRepository = new UserRepositoryImpl();
    private final static AuthServiceImpl service = new AuthServiceImpl(userRepository);

    public String registerUser(String userName, String fullName, String password){
        RegisterUserRequest request = new RegisterUserRequest();
        request.setFullName(fullName);
        request.setPassWord(password);
        request.setUserName(userName);

        RegisterUserResponse response = service.register(request);
        return response.getMessage();
    }

    public String loginUser(String userName, String password){
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUserName(userName);
        loginUserRequest.setPassword(password);

        LoginUserResponse response = service.login(loginUserRequest);
        return response.getMessage();
    }

    public String logout(String userName){
        LogOutRequest request = new LogOutRequest();
        request.setUserName(userName);

        LogoutUserResponse repsonse = service.logout(request);
        return repsonse.getMessage();
    }
}

