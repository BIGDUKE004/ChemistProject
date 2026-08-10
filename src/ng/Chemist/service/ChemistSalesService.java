package ng.Chemist.service;

import ng.Chemist.dtos.request.chemistSalesServiceRequest.sellDrugRequest;
import ng.Chemist.dtos.response.chemistSalesServiceResponse.sellDrugResponse;

public interface ChemistSalesService {
    sellDrugResponse sellDrug (sellDrugRequest request);
}
