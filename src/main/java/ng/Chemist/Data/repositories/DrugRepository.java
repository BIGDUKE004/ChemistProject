package ng.Chemist.Data.repositories;

import ng.Chemist.Data.model.Drug;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DrugRepository extends MongoRepository<Drug, Integer> {
    boolean existsByBrandNameAndDosage(String brandName, String dosage);
    Optional<Drug> findByBrandNameIgnoreCase(String brandName);
    List<Drug> findByGenericNameContainingIgnoreCase(String genericName);
    void deleteByBrandNameIgnoreCase(String brandName);
}

