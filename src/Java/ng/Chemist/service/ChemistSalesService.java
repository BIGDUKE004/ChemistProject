package Java.ng.Chemist.service;

import Java.ng.Chemist.dtos.request.chemistSalesServiceRequest.sellDrugRequest;
import Java.ng.Chemist.dtos.response.chemistSalesServiceResponse.sellDrugResponse;

public interface ChemistSalesService {
    sellDrugResponse sellDrug (sellDrugRequest request);
}
