package ng.Chemist.Data.repositories;

import ng.Chemist.Data.model.DispensedDrugsRecord;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface DispensedDrugsRecordRepository extends MongoRepository<DispensedDrugsRecord, String>{

}
