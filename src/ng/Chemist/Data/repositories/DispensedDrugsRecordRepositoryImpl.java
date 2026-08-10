package ng.Chemist.Data.repositories;

import ng.Chemist.Data.model.DispensedDrugsRecord;
import ng.Chemist.exceptions.repositoriesException.SalesRecordNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DispensedDrugsRecordRepositoryImpl implements DispensedDrugsRecordRepository {
    private ArrayList <DispensedDrugsRecord> records = new ArrayList<>();
    @Override
    public void addCurrentSales(DispensedDrugsRecord record) {
        this.records.add(record);
    }

    @Override
    public DispensedDrugsRecord viewRecord(LocalDateTime dateAndTime) {
        for(DispensedDrugsRecord record : records){
            if(record.getDateAndTime().equals(dateAndTime)){
                return record;
            }
        }
        throw new SalesRecordNotFoundException("Sales record not found");
    }

    @Override
    public boolean updateRecord(DispensedDrugsRecord record) {
        boolean status = false;
        for(int count = 0; count < records.size(); count++){
            if(records.get(count).getSaleId() == record.getSaleId()){
                records.set(count, record);
                status = true;
            }
        }
        return status;
    }

    @Override
    public void deleteRecord(int id) {
        for(int count = 0; count < records.size(); count++){
            if(records.get(count).getSaleId() == id){
                records.remove(count);
            } else {
                throw new SalesRecordNotFoundException("Sales record not found");
            }
        }
    }

    @Override
    public void deleteAll() {
        records.clear();
    }

    @Override
    public int countRecord() {
        return this.records.size();
    }
}
