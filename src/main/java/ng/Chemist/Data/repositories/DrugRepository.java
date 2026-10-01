package ng.Chemist.Data.repositories;

import ng.Chemist.Data.model.Drug;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DrugRepository extends MongoRepository<Drug, Integer> {
    boolean existsByBrandNameAndDosage(String brandName, String dosage);
    Optional<Drug> findByBrandNameIgnoreCaseAndStoreId(String brandName, String storeId);
    List<Drug> findByGenericNameContainingIgnoreCaseAndStoreId(String genericName, String storeId);
    List<Drug> findByStoreId(String storeId);
    void deleteByBrandNameIgnoreCaseAndStoreId(String brandName, String storeId);
    void deleteByStoreId(String storeId);
    long countByStoreId(String storeId);
}