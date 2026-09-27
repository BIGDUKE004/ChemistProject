package ng.Chemist.dtos.response.chemistSalesServiceResponse;

import lombok.Data;
import ng.Chemist.Data.model.DispenseDrug;
import ng.Chemist.Data.model.DispensedDrugsRecord;

import java.math.BigDecimal;
@Data
public class sellDrugResponse {
    private DispensedDrugsRecord record = new DispensedDrugsRecord();

}
