package ng.Chemist.util.authServiceUtil;

public class Password {
    public static boolean checkForUpperCase(String password){
        for(int count = 0; count < password.length(); count++){
            char letter = password.charAt(count);
            if(Character.isUpperCase(letter)){
                return true;
            }
        }
        return false;
    }

    public static boolean checkForDigit(String password){
        for(int count = 0; count < password.length(); count++){
            char letter = password.charAt(count);
            if(Character.isDigit(letter)){
                return true;
            }
        }
        return false;
    }
}
