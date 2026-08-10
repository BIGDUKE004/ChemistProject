package ng.Chemist.Data.model;

public class User {
    private String userName;
    private String passWord;
    private String fullName;
    private boolean isLoggedIn = false;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
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

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
