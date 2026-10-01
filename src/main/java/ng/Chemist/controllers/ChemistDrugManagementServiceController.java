package ng.Chemist.controllers;

import ng.Chemist.Data.model.Drug;
import ng.Chemist.dtos.request.chemistDrugManagementServiceRequest.*;
import ng.Chemist.dtos.response.chemistDrugManagementServiceResponse.*;
import ng.Chemist.security.AuthenticatedUser;
import ng.Chemist.service.ChemistDrugManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DrugManagement")
public class ChemistDrugManagementServiceController {

    @Autowired
    private ChemistDrugManagementServiceImpl chemistDrugService;

    private String storeId(Authentication authentication){
        return ((AuthenticatedUser) authentication.getPrincipal()).getStoreId();
    }

    @PostMapping("/AddDrug")
    public AddDrugResponse addDrug(@RequestBody AddDrugRequest addDrugRequest, Authentication authentication){
        return chemistDrugService.addDrug(addDrugRequest, storeId(authentication));
    }

    @PostMapping("/UpdateDrug")
    public UpdateDrugResponse updateDrug(@RequestBody UpdateDrugRequest updateDrugRequest, Authentication authentication){
        return chemistDrugService.updateDrug(updateDrugRequest, storeId(authentication));
    }

    @GetMapping("/GetAllDrugs")
    public List<Drug> getAllDrugs(Authentication authentication){
        return chemistDrugService.getAllDrugs(storeId(authentication));
    }

    @GetMapping("/ViewDrugDetails")
    public ViewDrugDetailResponse viewDrugDetail(ViewDrugDetailRequest request, Authentication authentication){
        return chemistDrugService.viewDrugDetail(request, storeId(authentication));
    }

    @GetMapping("/SearchDrug")
    public SearchDrugResponse searchDrug(SearchDrugRequest request, Authentication authentication){
        return chemistDrugService.searchDrug(request, storeId(authentication));
    }

    @DeleteMapping("/DeleteDrug")
    public DeleteDrugResponse deleteDrug(DeleteDrugRequest request, Authentication authentication){
        return chemistDrugService.deleteDrug(request, storeId(authentication));
    }

    @DeleteMapping("/DeleteAllDrug")
    public DeleteAllDrugResponse deleteAllDrug(@RequestBody DeleteAllDrugRequest deleteAllDrugRequest, Authentication authentication){
        return chemistDrugService.deleteAllDrug(deleteAllDrugRequest, storeId(authentication));
    }

    @GetMapping("/GetAmountOfDrugs")
    public GetAmountOfDrugsResponse getAmountOfDrugs(Authentication authentication){
        return chemistDrugService.getAmountOfDrugs(storeId(authentication));
    }
}