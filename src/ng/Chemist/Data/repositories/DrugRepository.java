package ng.Chemist.Data.repositories;

import ng.Chemist.Data.model.Drug;
import ng.Chemist.Data.model.User;

public interface DrugRepository{
    Drug add(Drug drug);
    boolean drugExistence(String drugName, String drugDosage);
    void updateDrug(Drug drug, int id);
    Drug findById(int id);
    void delete(int id);
    void deleteAll(boolean check);
    long count();
    Drug SearchByName(String name);
    Drug viewDrugInformation(String name);
}
