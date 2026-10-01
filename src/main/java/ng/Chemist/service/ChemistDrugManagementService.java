package ng.Chemist.service;

import ng.Chemist.Data.model.Drug;
import ng.Chemist.dtos.request.chemistDrugManagementServiceRequest.*;
import ng.Chemist.dtos.response.chemistDrugManagementServiceResponse.*;

import java.util.List;

public interface ChemistDrugManagementService {
    AddDrugResponse addDrug(AddDrugRequest request, String storeId);
    UpdateDrugResponse updateDrug(UpdateDrugRequest request, String storeId);
    List<Drug> getAllDrugs(String storeId);
    ViewDrugDetailResponse viewDrugDetail(ViewDrugDetailRequest request, String storeId);
    SearchDrugResponse searchDrug(SearchDrugRequest request, String storeId);
    DeleteDrugResponse deleteDrug(DeleteDrugRequest request, String storeId);
    DeleteAllDrugResponse deleteAllDrug(DeleteAllDrugRequest request, String storeId);
    GetAmountOfDrugsResponse getAmountOfDrugs(String storeId);
}