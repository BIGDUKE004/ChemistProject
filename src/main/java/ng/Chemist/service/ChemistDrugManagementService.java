package ng.Chemist.service;

import ng.Chemist.Data.model.Drug;
import ng.Chemist.dtos.request.chemistDrugManagementServiceRequest.*;
import ng.Chemist.dtos.response.chemistDrugManagementServiceResponse.*;

import java.util.List;

public interface ChemistDrugManagementService {
    AddDrugResponse addDrug(AddDrugRequest request);
    UpdateDrugResponse updateDrug(UpdateDrugRequest request);
    List<Drug> getAllDrugs();
    ViewDrugDetailResponse viewDrugDetail(ViewDrugDetailRequest request);
    SearchDrugResponse searchDrug(SearchDrugRequest request);
    DeleteDrugResponse deleteDrug(DeleteDrugRequest request);
    DeleteAllDrugResponse deleteAllDrug(DeleteAllDrugRequest request);
    GetAmountOfDrugsResponse getAmountOfDrugs();
}
