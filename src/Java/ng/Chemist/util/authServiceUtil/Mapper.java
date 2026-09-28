package Java.ng.Chemist.util.authServiceUtil;

import Java.ng.Chemist.Data.model.Drug;
import Java.ng.Chemist.dtos.request.authServiceRequest.RegisterUserRequest;
import Java.ng.Chemist.dtos.request.chemistDrugManagementServiceRequest.AddDrugRequest;
import Java.ng.Chemist.dtos.request.chemistDrugManagementServiceRequest.DeleteDrugRequest;
import Java.ng.Chemist.dtos.request.chemistDrugManagementServiceRequest.UpdateDrugRequest;
import Java.ng.Chemist.Data.model.User;

import java.math.BigDecimal;


public class Mapper {

    public static User mapToUser(RegisterUserRequest request){
        User user = new User();
            user.setFullName(request.getFullName());
            user.setUserName(request.getUserName());
            user.setPassWord(request.getPassWord());
        return user;
    }

    public static Drug mapToDrug(AddDrugRequest request) {
        Drug drug = new Drug();
        drug.setBatchNumber(request.getBatchNumber());
        drug.setBrandName(request.getBrandName());
        drug.setDosage(request.getDosage());
        drug.setStrength(request.getStrength());
        drug.setQuantityInStock(request.getQuantityInStock());
        drug.setPrice(BigDecimal.valueOf(request.getPrice()));
        drug.setId(request.getId());
        drug.setGenericName(request.getGenericName());
        drug.setManufacturer(request.getManufacturer());
        drug.setManufactureDate(request.getManufactureDate());
        drug.setExpiryDate(request.getExpiryDate());
        return drug;
    }

    public static Drug mapToDrugUpdate(UpdateDrugRequest request){
        Drug drug = new Drug();
        drug.setBatchNumber(request.getBatchNumber());
        drug.setBrandName(request.getBrandName());
        drug.setDosage(request.getDosage());
        drug.setStrength(request.getStrength());
        drug.setQuantityInStock(request.getQuantityInStock());
        drug.setPrice(BigDecimal.valueOf(request.getPrice()));
        drug.setId(request.getId());
        drug.setGenericName(request.getGenericName());
        drug.setManufacturer(request.getManufacturer());
        drug.setManufactureDate(request.getManufactureDate());
        drug.setExpiryDate(request.getExpiryDate());
        return drug;
    }


    public static Drug mapToDeleteDrugRequestToDrug(DeleteDrugRequest request) {
        Drug drug = new Drug();
        drug.setId(request.getId());
        return drug;
    }
}
