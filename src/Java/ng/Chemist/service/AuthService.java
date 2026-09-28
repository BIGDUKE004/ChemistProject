package Java.ng.Chemist.service;

import Java.ng.Chemist.dtos.request.authServiceRequest.LogOutRequest;
import Java.ng.Chemist.dtos.request.authServiceRequest.LoginUserRequest;
import Java.ng.Chemist.dtos.request.authServiceRequest.RegisterUserRequest;
import Java.ng.Chemist.dtos.response.authServiceResponse.LoginUserResponse;
import Java.ng.Chemist.dtos.response.authServiceResponse.LogoutUserResponse;
import Java.ng.Chemist.dtos.response.authServiceResponse.RegisterUserResponse;

public interface AuthService {
    RegisterUserResponse register(RegisterUserRequest request);
    LoginUserResponse login(LoginUserRequest request);
    LogoutUserResponse logout(LogOutRequest request);
}
