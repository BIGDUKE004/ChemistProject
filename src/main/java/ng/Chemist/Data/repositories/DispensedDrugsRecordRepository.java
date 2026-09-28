package ng.Chemist.Data.repositories;

import ng.Chemist.Data.model.DispensedDrugsRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DispensedDrugsRecordRepository extends MongoRepository<DispensedDrugsRecord, String>{

}
