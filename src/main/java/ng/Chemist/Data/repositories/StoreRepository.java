package ng.Chemist.Data.repositories;

import ng.Chemist.Data.model.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StoreRepository extends MongoRepository<Store, String> {
    Optional<Store> findByNameIgnoreCase(String name);
}