package ng.Chemist.controllers;

import ng.Chemist.Data.repositories.DrugRepository;
import ng.Chemist.dtos.request.chemistDrugManagementServiceRequest.*;
import ng.Chemist.dtos.response.chemistDrugManagementServiceResponse.*;
import ng.Chemist.service.ChemistDrugManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/DrugManagement")
public class ChemistDrugManagementServiceController {

    @Autowired
    private ChemistDrugManagementService chemistDrugService;

    @PostMapping("/AddDrug")
    public AddDrugResponse addDrug (@RequestBody AddDrugRequest addDrugRequest){
        return chemistDrugService.addDrug(addDrugRequest);
    }

    @PostMapping("/UpdateDrug")
    public UpdateDrugResponse updateDrug(@RequestBody UpdateDrugRequest updateDrugRequest){
        return chemistDrugService.updateDrug(updateDrugRequest);
    }

    @GetMapping("/ViewDrugDetails")
    public ViewDrugDetailResponse viewDrugDetail(@RequestBody ViewDrugDetailRequest viewDrugDetailRequest){
        return chemistDrugService.viewDrugDetail(viewDrugDetailRequest);
    }

    @GetMapping("/SearchDrug")
    public SearchDrugResponse searchDrug(@RequestBody SearchDrugRequest searchDrugRequest){
        return chemistDrugService.searchDrug(searchDrugRequest);
    }

    @DeleteMapping("/DeleteDrug")
    public DeleteDrugResponse deleteDrug(@RequestBody DeleteDrugRequest deleteDrugRequest){
        return chemistDrugService.deleteDrug(deleteDrugRequest);
    }

    @DeleteMapping("/DeleteAllDrug")
    public DeleteAllDrugResponse deleteAllDrug(@RequestBody DeleteAllDrugRequest deleteAllDrugRequest){
        return chemistDrugService.deleteAllDrug(deleteAllDrugRequest);
    }

    @GetMapping("/GetAmountOfDrugs")
    public GetAmountOfDrugsResponse getAmountOfDrugs(@RequestBody GetAmountOfDrugsRequest getAmountOfDrugsRequest){
        return chemistDrugService.getAmountOfDrugs(getAmountOfDrugsRequest);
    }

}
