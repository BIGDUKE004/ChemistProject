package ng.Chemist.Data.repositories;

import ng.Chemist.Data.model.DispensedDrugsRecord;

import java.time.LocalDateTime;

public interface DispensedDrugsRecordRepository {
    void addCurrentSales(DispensedDrugsRecord record);
    DispensedDrugsRecord viewRecord(LocalDateTime dateAndTime);
    boolean updateRecord(DispensedDrugsRecord record);
    void deleteRecord(int id);
    void deleteAll();
    int countRecord();
}
