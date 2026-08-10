package ng.Chemist.util.authServiceUtil;

public class AccountName {
    public static boolean checkIfItIsBlank(String userName, String fullName){
        if(userName.isBlank() || fullName.isBlank()){
            return true;
        }
        return false;
    }
}
