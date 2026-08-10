package ng.Chemist.controllers;

import ng.Chemist.Data.repositories.DrugRepository;
import ng.Chemist.Data.repositories.DrugRepositoryImpl;
import ng.Chemist.dtos.request.chemistDrugManagementServiceRequest.*;
import ng.Chemist.dtos.response.chemistDrugManagementServiceResponse.*;
import ng.Chemist.service.ChemistDrugManagementService;
import ng.Chemist.service.ChemistDrugManagementServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ChemistDrugManagementServiceController {
    private final static DrugRepository drugRepository = new DrugRepositoryImpl();
    private final static ChemistDrugManagementService chemistDrugService = new ChemistDrugManagementServiceImpl(drugRepository);

    public AddDrugResponse addDrug (int id, String brandName, String genericName, String strength, String dosage, String manufacturer, String batchNumber, LocalDate manufactureDate, LocalDate expiryDate, int price, int quantityInStock){
        AddDrugRequest addDrugRequest = new AddDrugRequest();
        addDrugRequest.setBatchNumber(batchNumber);
        addDrugRequest.setBrandName(brandName);
        addDrugRequest.setDosage(dosage);
        addDrugRequest.setStrength(strength);
        addDrugRequest.setQuantityInStock(quantityInStock);
        addDrugRequest.setPrice(price);
        addDrugRequest.setId(id);
        addDrugRequest.setGenericName(genericName);
        addDrugRequest.setManufacturer(manufacturer);
        addDrugRequest.setManufactureDate(manufactureDate);
        addDrugRequest.setExpiryDate(expiryDate);

        AddDrugResponse response = chemistDrugService.addDrug(addDrugRequest);
        return response;
    }

    public UpdateDrugResponse updateDrug(int id, String brandName, String genericName, String strength, String dosage, String manufacturer, String batchNumber, LocalDate manufactureDate, LocalDate expiryDate, int price, int quantityInStock){
        UpdateDrugRequest updateDrugRequest = new UpdateDrugRequest();
        updateDrugRequest.setBatchNumber(batchNumber);
        updateDrugRequest.setBrandName(brandName);
        updateDrugRequest.setDosage(dosage);
        updateDrugRequest.setStrength(strength);
        updateDrugRequest.setQuantityInStock(quantityInStock);
        updateDrugRequest.setPrice(price);
        updateDrugRequest.setId(id);
        updateDrugRequest.setGenericName(genericName);
        updateDrugRequest.setManufacturer(manufacturer);
        updateDrugRequest.setManufactureDate(manufactureDate);
        updateDrugRequest.setExpiryDate(expiryDate);

        UpdateDrugResponse response = chemistDrugService.updateDrug(updateDrugRequest);
        return response;
    }

    public ViewDrugDetailResponse viewDrugDetail(String brandName){
        ViewDrugDetailRequest viewDrugDetailRequest = new ViewDrugDetailRequest();
        viewDrugDetailRequest.setBrandName(brandName);

        ViewDrugDetailResponse response = chemistDrugService.viewDrugDetail(viewDrugDetailRequest);
        return response;
    }

    public SearchDrugResponse searchDrug(String drugName){
        SearchDrugRequest searchDrugRequest = new SearchDrugRequest();
        searchDrugRequest.setDrugName(drugName);

        SearchDrugResponse response = chemistDrugService.searchDrug(searchDrugRequest);
        return response;
    }

    public DeleteDrugResponse deleteDrug(int id){
        DeleteDrugRequest deleteDrugRequest = new DeleteDrugRequest();
        deleteDrugRequest.setDrugId(id);

        DeleteDrugResponse response = chemistDrugService.deleteDrug(deleteDrugRequest);
        return response;
    }

//    public DeleteAllDrugResponse deleteAllDrug(String userResponse){
//        DeleteAllDrugRequest deleteAllDrugRequest =  new DeleteAllDrugRequest();
//        if(userResponse.equalsIgnoreCase("yes")){
//            deleteAllDrugRequest.deleteAllSwitch(true);
//            DeleteDrugResponse response = chemistDrugService.deleteAllDrug(deleteAllDrugRequest);
//        }
//
//        DeleteDrugResponse res
//    }

}
