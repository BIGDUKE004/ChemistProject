package ng.Chemist.dtos.request.chemistSalesServiceRequest;

import ng.Chemist.Data.model.DispenseDrug;
import ng.Chemist.Data.model.Drug;

import java.util.ArrayList;

public class sellDrugRequest {
    private ArrayList <DispenseDrug> drugs = new ArrayList<>();
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDrug(DispenseDrug drug){
        this.drugs.add(drug);
    }

    public ArrayList<DispenseDrug> getDrug(){
        return this.drugs;
    }


}
