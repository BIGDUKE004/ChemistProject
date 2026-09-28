package ng.Chemist.controllers;

import ng.Chemist.dtos.request.authServiceRequest.LogOutRequest;
import ng.Chemist.dtos.request.authServiceRequest.LoginUserRequest;
import ng.Chemist.dtos.request.authServiceRequest.RegisterUserRequest;
import ng.Chemist.dtos.response.authServiceResponse.LoginUserResponse;
import ng.Chemist.dtos.response.authServiceResponse.LogoutUserResponse;
import ng.Chemist.dtos.response.authServiceResponse.RegisterUserResponse;
import ng.Chemist.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Authorization")
public class AuthServiceController {
    @Autowired
    private AuthServiceImpl service;

    @PostMapping("/Register")
    public RegisterUserResponse registerUser(@RequestBody RegisterUserRequest request){
        return service.register(request);
    }

    @PostMapping("/Login")
    public LoginUserResponse loginUser(@RequestBody LoginUserRequest request){
        return service.login(request);
    }

    @PostMapping("/Logout")
    public LogoutUserResponse logout(@RequestBody LogOutRequest request){
        return service.logout(request);
    }
}

