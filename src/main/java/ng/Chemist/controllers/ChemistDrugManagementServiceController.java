package ng.Chemist.controllers;

import ng.Chemist.Data.model.Drug;
import ng.Chemist.dtos.request.chemistDrugManagementServiceRequest.*;
import ng.Chemist.dtos.response.chemistDrugManagementServiceResponse.*;
import ng.Chemist.service.ChemistDrugManagementService;
import ng.Chemist.service.ChemistDrugManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DrugManagement")
public class ChemistDrugManagementServiceController {

    @Autowired
    private ChemistDrugManagementServiceImpl chemistDrugService;

    @PostMapping("/AddDrug")
    public AddDrugResponse addDrug(@RequestBody AddDrugRequest addDrugRequest){
        return chemistDrugService.addDrug(addDrugRequest);
    }

    @PostMapping("/UpdateDrug")
    public UpdateDrugResponse updateDrug(@RequestBody UpdateDrugRequest updateDrugRequest){
        return chemistDrugService.updateDrug(updateDrugRequest);
    }

    @GetMapping("/GetAllDrugs")
    public List<Drug> getAllDrugs(){
        return chemistDrugService.getAllDrugs();
    }

    @GetMapping("/ViewDrugDetails")
    public ViewDrugDetailResponse viewDrugDetail(@RequestParam ViewDrugDetailRequest request){
        return chemistDrugService.viewDrugDetail(request);
    }

    @GetMapping("/SearchDrug")
    public SearchDrugResponse searchDrug(@RequestParam SearchDrugRequest request){
        return chemistDrugService.searchDrug(request);
    }

    @DeleteMapping("/DeleteDrug")
    public DeleteDrugResponse deleteDrug(@RequestParam DeleteDrugRequest request){
        return chemistDrugService.deleteDrug(request);
    }

    @DeleteMapping("/DeleteAllDrug")
    public DeleteAllDrugResponse deleteAllDrug(@RequestBody DeleteAllDrugRequest deleteAllDrugRequest){
        return chemistDrugService.deleteAllDrug(deleteAllDrugRequest);
    }

    @GetMapping("/GetAmountOfDrugs")
    public GetAmountOfDrugsResponse getAmountOfDrugs(){
        return chemistDrugService.getAmountOfDrugs();
    }
}