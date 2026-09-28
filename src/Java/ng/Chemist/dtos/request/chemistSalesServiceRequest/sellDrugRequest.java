package Java.ng.Chemist.dtos.request.chemistSalesServiceRequest;

import lombok.Data;
import Java.ng.Chemist.Data.model.DispenseDrug;

import java.util.ArrayList;
@Data
public class sellDrugRequest {
    private ArrayList <DispenseDrug> drugs = new ArrayList<>();
    private String name;

}
