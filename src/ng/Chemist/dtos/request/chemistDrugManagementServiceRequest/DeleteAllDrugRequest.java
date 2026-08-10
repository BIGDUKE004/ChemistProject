package ng.Chemist.dtos.request.chemistDrugManagementServiceRequest;

public class DeleteAllDrugRequest {
    boolean deleteSwitch = false;

    public void deleteAllSwitch(boolean deleteSwitch){
        this.deleteSwitch = deleteSwitch;
    }

    public boolean getSwitch(){
        return this.deleteSwitch;
    }
}
