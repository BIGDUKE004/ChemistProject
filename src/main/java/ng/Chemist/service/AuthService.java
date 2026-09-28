package ng.Chemist.service;

import ng.Chemist.dtos.request.authServiceRequest.LogOutRequest;
import ng.Chemist.dtos.request.authServiceRequest.LoginUserRequest;
import ng.Chemist.dtos.request.authServiceRequest.RegisterUserRequest;
import ng.Chemist.dtos.response.authServiceResponse.LoginUserResponse;
import ng.Chemist.dtos.response.authServiceResponse.LogoutUserResponse;
import ng.Chemist.dtos.response.authServiceResponse.RegisterUserResponse;

public interface AuthService {
    RegisterUserResponse register(RegisterUserRequest request);
    LoginUserResponse login(LoginUserRequest request);
    LogoutUserResponse logout(LogOutRequest request);
}
