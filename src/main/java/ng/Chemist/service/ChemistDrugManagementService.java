package ng.Chemist.service;

import ng.Chemist.dtos.request.chemistDrugManagementServiceRequest.*;
import ng.Chemist.dtos.response.chemistDrugManagementServiceResponse.*;
import ng.Chemist.dtos.request.chemistDrugManagementServiceRequest.*;
import ng.Chemist.dtos.response.chemistDrugManagementServiceResponse.*;

public interface ChemistDrugManagementService {
    AddDrugResponse addDrug (AddDrugRequest request);
    UpdateDrugResponse updateDrug (UpdateDrugRequest request);
    ViewDrugDetailResponse viewDrugDetail (ViewDrugDetailRequest request);
    SearchDrugResponse searchDrug (SearchDrugRequest request);
    DeleteDrugResponse deleteDrug (DeleteDrugRequest request);
    DeleteAllDrugResponse deleteAllDrug (DeleteAllDrugRequest request);
    GetAmountOfDrugsResponse getAmountOfDrugs (GetAmountOfDrugsRequest request);
}
