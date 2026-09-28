package Java.ng.Chemist.dtos.request.authServiceRequest;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String userName;
    private String passWord;
    private String fullName;

}
