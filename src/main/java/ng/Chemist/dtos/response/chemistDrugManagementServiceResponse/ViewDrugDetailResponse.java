package ng.Chemist.dtos.response.chemistDrugManagementServiceResponse;

import lombok.Data;
import ng.Chemist.Data.model.Drug;

@Data
public class ViewDrugDetailResponse {
    private Drug drug;
    private String message;
}