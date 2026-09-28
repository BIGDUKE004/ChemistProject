package Java.ng.Chemist.dtos.response.authServiceResponse;

import lombok.Data;

@Data
public class RegisterUserResponse {
    private String userName;
    private String fullName;
    private String userId;
    private boolean isLoggedIn;
}
