package Java.ng.Chemist.dtos.request.authServiceRequest;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String userName;
    private String password;
}
