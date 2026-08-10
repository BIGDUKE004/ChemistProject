package ng.Chemist.service;

import ng.Chemist.Data.model.Drug;
import ng.Chemist.Data.repositories.DrugRepository;
import ng.Chemist.dtos.request.chemistDrugManagementServiceRequest.*;
import ng.Chemist.dtos.response.chemistDrugManagementServiceResponse.*;
import ng.Chemist.exceptions.ChemistDrugManagementServiceException.FillInEveryInformationException;
import ng.Chemist.util.authServiceUtil.Mapper;
import ng.Chemist.util.ChemistDrugManagementServiceUtil.IsEmptyCheck;

public class ChemistDrugManagementServiceImpl implements ChemistDrugManagementService{
    private DrugRepository drugRepository;

    public ChemistDrugManagementServiceImpl(DrugRepository drugRepository){
        this.drugRepository = drugRepository;
    }
    @Override
    public AddDrugResponse addDrug(AddDrugRequest request) {
        AddDrugResponse response = new AddDrugResponse();
        Drug drug = Mapper.mapToDrug(request);
        boolean checkForEmptyInformation = IsEmptyCheck.checkIfItIsEmpty(drug);
        if(checkForEmptyInformation == true){
            throw new FillInEveryInformationException("Please fill in all information");
        }
        this.drugRepository.add(drug);
        response.setMessage("Drug added successfully");
        return response;
    }

    @Override
    public UpdateDrugResponse updateDrug(UpdateDrugRequest request) {
        UpdateDrugResponse response = new UpdateDrugResponse();
        Drug drug = Mapper.mapToDrugUpdate(request);
        this.drugRepository.updateDrug(drug, request.getId());
        response.setMessage("Drug information updated successfully");
        return response;
    }

    @Override
    public ViewDrugDetailResponse viewDrugDetail(ViewDrugDetailRequest request) {
        Drug drug = this.drugRepository.SearchByName(request.getBrandName());
        ViewDrugDetailResponse response = new ViewDrugDetailResponse();
        response.setMessage(drug.toString());
        return response;
    }

    @Override
    public SearchDrugResponse searchDrug(SearchDrugRequest request) {
        SearchDrugResponse searchDrugResponse = new SearchDrugResponse();
        Drug drug = this.drugRepository.SearchByName(request.getDrugName());
        String message = drug.getGenericName() + " " + drug.getStrength() + " " + drug.getDosage();
        searchDrugResponse.setMessage(message);
        return searchDrugResponse;
    }

    @Override
    public DeleteDrugResponse deleteDrug(DeleteDrugRequest request) {
        DeleteDrugResponse deleteDrugResponse = new DeleteDrugResponse();
        this.drugRepository.delete(request.getDrugId());
        deleteDrugResponse.setMessage("Drug deleted successfully");
        return deleteDrugResponse;
    }

    @Override
    public DeleteAllDrugResponse deleteAllDrug(DeleteAllDrugRequest request) {
        DeleteAllDrugResponse deleteAllDrugResponse = new DeleteAllDrugResponse();
        this.drugRepository.deleteAll(request.getSwitch());
        deleteAllDrugResponse.setMessage("Drug deleted successfully");
        return deleteAllDrugResponse;
    }

    @Override
    public GetAmountOfDrugsResponse getAmountOfDrugs(GetAmountOfDrugsRequest request) {
        GetAmountOfDrugsResponse getAmountOfDrugsResponse = new GetAmountOfDrugsResponse();
        if(request.getRequestSwitch() == true){
            long count = this.drugRepository.count();
            String message = "The Amount Of Drugs is " + count;
            getAmountOfDrugsResponse.setMessage(message);
        }
        return getAmountOfDrugsResponse;
    }
}
