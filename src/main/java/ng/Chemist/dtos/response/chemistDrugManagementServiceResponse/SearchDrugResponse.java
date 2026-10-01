package ng.Chemist.dtos.response.chemistDrugManagementServiceResponse;

import lombok.Data;
import ng.Chemist.Data.model.Drug;

import java.util.List;

@Data
public class SearchDrugResponse {
    private List<Drug> drugs;
    private String message;
}
