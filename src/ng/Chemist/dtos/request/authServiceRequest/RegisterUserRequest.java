package ng.Chemist.dtos.request.authServiceRequest;

public class RegisterUserRequest {
    private String userName;
    private String passWord;
    private String fullName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassWord() {
        return passWord;
    }
}
