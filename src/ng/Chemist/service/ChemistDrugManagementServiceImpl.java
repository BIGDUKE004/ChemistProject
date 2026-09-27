package ng.Chemist.service;

import ng.Chemist.Data.model.Drug;
import ng.Chemist.Data.repositories.DrugRepository;
import ng.Chemist.dtos.request.chemistDrugManagementServiceRequest.*;
import ng.Chemist.dtos.response.chemistDrugManagementServiceResponse.*;
import ng.Chemist.exceptions.ChemistDrugManagementServiceException.FillInEveryInformationException;
import ng.Chemist.exceptions.repositoriesException.DrugDoesNotExistException;
import ng.Chemist.util.authServiceUtil.Mapper;
import ng.Chemist.util.ChemistDrugManagementServiceUtil.IsEmptyCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChemistDrugManagementServiceImpl implements ChemistDrugManagementService{
    @Autowired
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
        this.drugRepository.save(drug);
        response.setMessage("Drug added successfully");
        return response;
    }

    @Override
    public UpdateDrugResponse updateDrug(UpdateDrugRequest request) {
        UpdateDrugResponse response = new UpdateDrugResponse();
        Optional<Drug> drug = drugRepository.findById(request.getId());
        if(drug.isEmpty()) {
            throw new DrugDoesNotExistException("Drug not found");
        } else {
            drug.get().setId(request.getId());
            drug.get().setQuantityInStock(request.getQuantityInStock());
            drug.get().setDosage(request.getDosage());
            drug.get().setStrength(request.getStrength());
            drug.get().setExpiryDate(request.getExpiryDate());
            drug.get().setManufactureDate(request.getManufactureDate());
            drug.get().setGenericName(request.getGenericName());
            drug.get().setBatchNumber(request.getBatchNumber());
            drug.get().setBrandName(request.getBrandName());

            this.drugRepository.save(drug.get());

            response.setMessage("Drug information updated successfully");
            return response;
        }
//        response.setMessage("");
//        return response;
    }

    @Override
    public ViewDrugDetailResponse viewDrugDetail(ViewDrugDetailRequest request) {
        Drug drug = this.drugRepository.searchByBrandName(request.getBrandName());
        ViewDrugDetailResponse response = new ViewDrugDetailResponse();
        response.setMessage(drug.toString());
        return response;
    }

    @Override
    public SearchDrugResponse searchDrug(SearchDrugRequest request) {
        SearchDrugResponse searchDrugResponse = new SearchDrugResponse();
        Drug drug = this.drugRepository.searchByGenericName(request.getGenericName());
        if (drug == null) {
            searchDrugResponse.setMessage("Drug not found");
            return searchDrugResponse;
        }
        String message = drug.getGenericName() + " " + drug.getStrength() + " " + drug.getDosage();
        searchDrugResponse.setMessage(message);
        return searchDrugResponse;
    }

    @Override
    public DeleteDrugResponse deleteDrug(DeleteDrugRequest request) {
        DeleteDrugResponse deleteDrugResponse = new DeleteDrugResponse();
        Drug drug = Mapper.mapToDeleteDrugRequestToDrug(request);
        if(this.drugRepository.findById(drug.getId()).isEmpty()){
            throw new DrugDoesNotExistException("Drug not found");
        }
        this.drugRepository.delete(drug);
        deleteDrugResponse.setMessage("Drug deleted successfully");
        return deleteDrugResponse;
    }

    @Override
    public DeleteAllDrugResponse deleteAllDrug(DeleteAllDrugRequest request) {
        DeleteAllDrugResponse deleteAllDrugResponse = new DeleteAllDrugResponse();
        if(request.getOption().equalsIgnoreCase("yes")){
            this.drugRepository.deleteAll();
        } else {
            throw new IllegalArgumentException("Wrong Command, Type 'Yes'");
        }
        deleteAllDrugResponse.setMessage("Drug deleted successfully");
        return deleteAllDrugResponse;
    }

    @Override
    public GetAmountOfDrugsResponse getAmountOfDrugs(GetAmountOfDrugsRequest request) {
        GetAmountOfDrugsResponse getAmountOfDrugsResponse = new GetAmountOfDrugsResponse();
        if(request.isRequestSwitch() == true){
            long count = this.drugRepository.count();
            String message = "The Amount Of Drugs is " + count;
            getAmountOfDrugsResponse.setMessage(message);
        }
        return getAmountOfDrugsResponse;
    }
}
