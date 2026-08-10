package ng.Chemist.Data.repositories;

import ng.Chemist.Data.model.Drug;
import ng.Chemist.exceptions.repositoriesException.DrugDoesNotExistException;
import ng.Chemist.exceptions.repositoriesException.SameIdException;

import java.util.ArrayList;

public class DrugRepositoryImpl implements DrugRepository{
    private  ArrayList <Drug> drugs = new ArrayList<>();

    @Override
    public Drug add(Drug drug) {
        boolean check = drugExistence(drug.getBrandName(), drug.getDosage());
        if(check == true){
            throw new SameIdException("Id Already exist");
        }
        drugs.add(drug);
        return drug;
    }

    @Override
    public boolean drugExistence(String drugName, String drugDosage){
        boolean check= false;
        for(int count = 0; count < drugs.size(); count++){
            if(drugs.get(count).getDosage().equalsIgnoreCase(drugDosage) && drugs.get(count).getBrandName().equalsIgnoreCase(drugName)){
                check = true;
            }
        }
        return check;
    }

    @Override
    public void updateDrug(Drug drug, int id) {
        for(int count = 0; count < drugs.size(); count++){
            if(drugs.get(count).getId() == id){
                drugs.set(count, drug);
                return;
            }
        }

        throw new DrugDoesNotExistException("Drug not in system, Add drug now");
    }

    @Override
    public Drug findById(int id) {
        for(Drug drug : drugs){
            if(drug.getId() == id){
                return drug;
            }
        }
        throw new DrugDoesNotExistException("Drug not found");
    }

    @Override
    public void delete(int id) {
        for(int count = 0; count < drugs.size(); count++){
            if(drugs.get(count).getId() == id){
                drugs.remove(count);
                return;
            }
        }

        throw new DrugDoesNotExistException("Drug not found");
    }

    @Override
    public void deleteAll(boolean check) {
        if(check == true){
            drugs.clear();
        }
    }

    @Override
    public long count() {
        return drugs.size();
    }

    @Override
    public Drug SearchByName(String name) {
        for (int count = 0; count < drugs.size(); count++) {
            if (drugs.get(count).getBrandName().equalsIgnoreCase(name) || drugs.get(count).getGenericName().equalsIgnoreCase(name)) {
                return drugs.get(count);
            }
        }
        throw new DrugDoesNotExistException("Drug not found");
    }

    @Override
    public Drug viewDrugInformation(String name) {
        for (int count = 0; count < drugs.size(); count++) {
            if (drugs.get(count).getBrandName().equalsIgnoreCase(name)) {
                return drugs.get(count);
            }
        }
        throw new DrugDoesNotExistException("Drug not found");    }
}
