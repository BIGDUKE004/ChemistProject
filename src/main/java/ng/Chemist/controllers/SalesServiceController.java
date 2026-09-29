package ng.Chemist.controllers;

import ng.Chemist.dtos.request.chemistSalesServiceRequest.sellDrugRequest;
import ng.Chemist.dtos.response.chemistSalesServiceResponse.sellDrugResponse;
import ng.Chemist.service.ChemistSalesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Sales")
public class SalesServiceController {
    @Autowired
    private ChemistSalesServiceImpl chemistSalesService;

    @PostMapping("/dispenseDrug")
    public sellDrugResponse sellDrug(sellDrugRequest drugRequest){
        return chemistSalesService.sellDrug(drugRequest);
    }

}
