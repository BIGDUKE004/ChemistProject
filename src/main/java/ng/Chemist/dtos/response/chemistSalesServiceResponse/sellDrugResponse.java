package ng.Chemist.dtos.response.chemistSalesServiceResponse;

import lombok.Data;
import ng.Chemist.Data.model.DispensedDrugsRecord;

@Data
public class sellDrugResponse {
    private DispensedDrugsRecord record = new DispensedDrugsRecord();

}
