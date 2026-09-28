package Java.ng.Chemist.util.ChemistDrugManagementServiceUtil;

import Java.ng.Chemist.Data.model.Drug;

public class IsEmptyCheck {
    public static boolean checkIfItIsEmpty(Drug drug){
        boolean check = false;
        if(drug.getBrandName().isBlank() || drug.getGenericName().isBlank() || drug.getDosage().isBlank() || drug.getManufacturer().isBlank() || drug.getStrength().isBlank()){
            check = true;
        }
        return check;
    }

}
