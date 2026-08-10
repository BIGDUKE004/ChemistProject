package ng.Chemist.dtos.request.chemistDrugManagementServiceRequest;

public class GetAmountOfDrugsRequest {
    private boolean requestSwitch;

    public void setRequestSwitch(boolean requestSwitch){
        this.requestSwitch = requestSwitch;
    }

    public boolean getRequestSwitch(){
        return this.requestSwitch;
    }
}
