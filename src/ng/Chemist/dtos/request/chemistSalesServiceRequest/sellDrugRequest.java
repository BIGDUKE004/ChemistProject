package ng.Chemist.dtos.request.chemistSalesServiceRequest;

import lombok.Data;
import ng.Chemist.Data.model.DispenseDrug;
import ng.Chemist.Data.model.Drug;

import java.util.ArrayList;
@Data
public class sellDrugRequest {
    private ArrayList <DispenseDrug> drugs = new ArrayList<>();
    private String name;

}
