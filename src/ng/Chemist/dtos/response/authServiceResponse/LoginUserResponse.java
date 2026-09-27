package ng.Chemist.dtos.response.authServiceResponse;

import lombok.Data;

@Data
public class LoginUserResponse {
    private String userName;
    private String fullName;
    private String jwtId;

}
