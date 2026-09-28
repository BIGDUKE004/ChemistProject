package ng.Chemist.Data.repositories;

import ng.Chemist.Data.model.Drug;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DrugRepository extends MongoRepository<Drug, Integer> {
    boolean existsByBrandNameAndDosage(String brandName, String dosage);
    Drug searchByBrandName(String brandName);
    Drug findByBrandName(String brandName);
    Drug searchByGenericName(String genericName);
}
