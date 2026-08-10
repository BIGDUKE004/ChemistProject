package ng.Chemist.dtos.request.chemistDrugManagementServiceRequest;

public class SearchDrugRequest {
    private String drug;

    public void setDrugName(String drugName) {
        this.drug = drugName;
    }

    public String getDrugName() {
        return drug;
    }
}
