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

import java.util.List;
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
    public List<Drug> getAllDrugs() {
        return drugRepository.findAll();
    }

    @Override
    public ViewDrugDetailResponse viewDrugDetail(ViewDrugDetailRequest request) {
        Drug drug = drugRepository.findByBrandNameIgnoreCase(request.getBrandName())
                .orElseThrow(() -> new DrugDoesNotExistException("Drug not found"));
        ViewDrugDetailResponse response = new ViewDrugDetailResponse();
        response.setDrug(drug);
        response.setMessage("Drug found");
        return response;
    }

    @Override
    public SearchDrugResponse searchDrug(SearchDrugRequest request) {
        List<Drug> drugs = drugRepository.findByGenericNameContainingIgnoreCase(request.getGenericName());
        SearchDrugResponse response = new SearchDrugResponse();
        response.setDrugs(drugs);
        response.setMessage(drugs.isEmpty() ? "No drugs found" : drugs.size() + " drug(s) found");
        return response;
    }

    @Override
    public DeleteDrugResponse deleteDrug(DeleteDrugRequest request) {
        Drug drug = drugRepository.findByBrandNameIgnoreCase(request.getBrandName())
                .orElseThrow(() -> new DrugDoesNotExistException("Drug not found"));
        drugRepository.delete(drug);
        DeleteDrugResponse response = new DeleteDrugResponse();
        response.setMessage("Drug deleted successfully");
        return response;
    }

    @Override
    public GetAmountOfDrugsResponse getAmountOfDrugs() {
        GetAmountOfDrugsResponse response = new GetAmountOfDrugsResponse();
        response.setMessage("The Amount Of Drugs is " + drugRepository.count());
        return response;
    }
}