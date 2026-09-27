//package ng.chemist.repositoryTest;
//
//import ng.Chemist.Data.model.DispenseDrug;
//import ng.Chemist.Data.model.DispensedDrugsRecord;
//import ng.Chemist.Data.model.Drug;
//import ng.Chemist.Data.model.User;
//import ng.Chemist.Data.repositories.*;
//import ng.Chemist.exceptions.repositoriesException.SalesRecordNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class DispensedDrugsRecordRepositoryTest {
//    @Test
//    public void testThatUserCanAddSalesRecordOfOneDrugSold(){
////        Drug drug = new Drug();
////        drug.setBatchNumber("EMP2026001");
////        drug.setBrandName("Emzor Paracetamol");
////        drug.setDosage("Tablet");
////        drug.setStrength("500mg");
////        drug.setQuantityInStock(100);
////        drug.setPrice(500);
////        drug.setId(101);
////        drug.setGenericName("paracetamol");
////        drug.setManufacturer("Emzor");
////        drug.setManufactureDate(LocalDate.of(2026,1,10));
////        drug.setExpiryDate(LocalDate.of(2028, 1, 10));
//
//        DispenseDrug dispenseDrug = new DispenseDrug();
//        dispenseDrug.setBatchId("2026");
//        dispenseDrug.setId(101);
//        dispenseDrug.setDrug("Emzor Paracetamol");
//        dispenseDrug.setQuantity(5);
//
//        ArrayList <String> drugg = new ArrayList<>();
//        drugg.add("Emzor Paracetamol");
//
//        ArrayList <DispenseDrug> drugs = new ArrayList<>();
//        drugs.add(dispenseDrug);
//
//        DispensedDrugsRecordRepositoryImpl drugsRecordRepository = new DispensedDrugsRecordRepositoryImpl();
//
//        DispensedDrugsRecord record = new DispensedDrugsRecord();
//        record.setAmount(BigDecimal.valueOf(500));
//        record.setDrug(drugg);
//        record.setUser("Miracle");
//        record.setDateAndTime(LocalDateTime.now());
//        record.setQuantitySold(1);
//        record.setSaleId(101);
//
//        drugsRecordRepository.addCurrentSales(record);
//
//        assertEquals(1, drugsRecordRepository.countRecord());
//    }
//
//    @Test
//    public void testThatUserAddDrugs_UserViewSalesRecord(){
////        Drug drug = new Drug();
////        drug.setBatchNumber("EMP2026001");
////        drug.setBrandName("Emzor Paracetamol");
////        drug.setDosage("Tablet");
////        drug.setStrength("500mg");
////        drug.setQuantityInStock(100);
////        drug.setPrice(500);
////        drug.setId(101);
////        drug.setGenericName("paracetamol");
////        drug.setManufacturer("Emzor");
////        drug.setManufactureDate(LocalDate.of(2026,1,10));
////        drug.setExpiryDate(LocalDate.of(2028, 1, 10));
//
//        DispenseDrug dispenseDrug = new DispenseDrug();
//        dispenseDrug.setBatchId("2026");
//        dispenseDrug.setId(101);
//        dispenseDrug.setDrug("Emzor Paracetamol");
//        dispenseDrug.setQuantity(5);
//
//        ArrayList <DispenseDrug> drugs = new ArrayList<>();
//        drugs.add(dispenseDrug);
//
//        ArrayList <String> drugg = new ArrayList<>();
//        drugg.add("Emzor Paracetamol");
//
//        DispensedDrugsRecordRepositoryImpl drugsRecordRepository = new DispensedDrugsRecordRepositoryImpl();
//
//        DispensedDrugsRecord record = new DispensedDrugsRecord();
//        record.setAmount(BigDecimal.valueOf(500));
//        record.setDrug(drugg);
//        record.setUser("miracle");
//        record.setDateAndTime(LocalDateTime.of(2026, 10, 31, 15, 45));
//        record.setQuantitySold(1);
//        record.setSaleId(101);
//        drugsRecordRepository.addCurrentSales(record);
//
//        assertEquals(1, drugsRecordRepository.countRecord());
//
//        DispensedDrugsRecord viewSalesRecord = drugsRecordRepository.viewRecord(LocalDateTime.of(2026, 10, 31, 15, 45));
//        assertEquals(1, viewSalesRecord.getQuantitySold());
//        assertEquals(101, viewSalesRecord.getSaleId());
//    }
//
//    @Test
//    public void testThatUserAddDrugs_UserSearchesForRecordThatIsNOtInTheSystem(){
////        Drug drug = new Drug();
////        drug.setBatchNumber("EMP2026001");
////        drug.setBrandName();
////        drug.setDosage("Tablet");
////        drug.setStrength("500mg");
////        drug.setQuantityInStock(100);
////        drug.setPrice(500);
////        drug.setId(101);
////        drug.setGenericName("paracetamol");
////        drug.setManufacturer("Emzor");
////        drug.setManufactureDate(LocalDate.of(2026,1,10));
////        drug.setExpiryDate(LocalDate.of(2028, 1, 10));
//
//        DispenseDrug dispenseDrug = new DispenseDrug();
//        dispenseDrug.setBatchId("2026");
//        dispenseDrug.setId(101);
//        dispenseDrug.setDrug("Emzor Paracetamol");
//        dispenseDrug.setQuantity(5);
//
//        ArrayList <DispenseDrug> drugs = new ArrayList<>();
//        drugs.add(dispenseDrug);
//
//        ArrayList <String> drugg = new ArrayList<>();
//        drugg.add("Emzor Paracetamol");
//
//        DispensedDrugsRecordRepositoryImpl drugsRecordRepository = new DispensedDrugsRecordRepositoryImpl();
//        DispensedDrugsRecord record = new DispensedDrugsRecord();
//        record.setAmount(BigDecimal.valueOf(500));
//        record.setDrug(drugg);
//        record.setUser("miracle");
//        record.setDateAndTime(LocalDateTime.of(2026, 10, 31, 15, 45));
//        record.setQuantitySold(1);
//        record.setSaleId(101);
//        drugsRecordRepository.addCurrentSales(record);
//
//        assertEquals(1, drugsRecordRepository.countRecord());
//
//        assertThrows(SalesRecordNotFoundException.class, () -> drugsRecordRepository.viewRecord(LocalDateTime.of(2025, 10, 31, 15, 45)));
//    }
//
//    @Test
//    public void testThatUserAddsSalesRecord_UserMadeAMistake_UserUpdatesSalesRecord(){
//
//        DispenseDrug dispenseDrug = new DispenseDrug();
//        dispenseDrug.setBatchId("2026");
//        dispenseDrug.setId(101);
//        dispenseDrug.setDrug("Emzor Paracetamol");
//        dispenseDrug.setQuantity(5);
//
//        ArrayList <DispenseDrug> drugs = new ArrayList<>();
//        drugs.add(dispenseDrug);
//
//        ArrayList <String> drugg = new ArrayList<>();
//        drugg.add("Emzor Paracetamol");
//
//        DispensedDrugsRecordRepositoryImpl drugsRecordRepository = new DispensedDrugsRecordRepositoryImpl();
//        DispensedDrugsRecord record = new DispensedDrugsRecord();
//        record.setAmount(BigDecimal.valueOf(500));
//        record.setDrug(drugg);
//        record.setUser("miracle");
//        record.setDateAndTime(LocalDateTime.of(2026, 10, 31, 15, 45));
//        record.setQuantitySold(1);
//        record.setSaleId(101);
//        drugsRecordRepository.addCurrentSales(record);
//
//        assertEquals(1, drugsRecordRepository.countRecord());
//
//        DispensedDrugsRecord viewSalesRecord = drugsRecordRepository.viewRecord(LocalDateTime.of(2026, 10, 31, 15, 45));
//        assertEquals(1, viewSalesRecord.getQuantitySold());
//        assertEquals(101, viewSalesRecord.getSaleId());
//
//        ArrayList <String> listOfDrugs = new ArrayList<>();
//        drugg.add("Emzor Paracetamol");
//
//        DispensedDrugsRecord updatedRecord = new DispensedDrugsRecord();
//        updatedRecord.setAmount(BigDecimal.valueOf(500));
//        updatedRecord.setDrug(listOfDrugs);
//        updatedRecord.setUser("miracle");
//        updatedRecord.setDateAndTime(LocalDateTime.of(2026, 10, 31, 15, 45));
//        updatedRecord.setQuantitySold(1);
//        updatedRecord.setSaleId(101);
//        assertTrue(drugsRecordRepository.updateRecord(updatedRecord));
//
//    }
//
//    @Test
//    public void testThatUserCanDeleteSalesFromRecord(){
//
//        DispenseDrug dispenseDrug = new DispenseDrug();
//        dispenseDrug.setBatchId("2026");
//        dispenseDrug.setId(101);
//        dispenseDrug.setDrug("Emzor Paracetamol");
//        dispenseDrug.setQuantity(5);
//
//        ArrayList <DispenseDrug> drugs = new ArrayList<>();
//        drugs.add(dispenseDrug);
//
//        ArrayList <String> drugg = new ArrayList<>();
//        drugg.add("Emzor Paracetamol");
//
//        DispensedDrugsRecordRepositoryImpl drugsRecordRepository = new DispensedDrugsRecordRepositoryImpl();
//        DispensedDrugsRecord record = new DispensedDrugsRecord();
//        record.setAmount(BigDecimal.valueOf(500));
//        record.setDrug(drugg);
//        record.setUser("miracle");
//        record.setDateAndTime(LocalDateTime.now());
//        record.setQuantitySold(1);
//        record.setSaleId(101);
//        drugsRecordRepository.addCurrentSales(record);
//
//        assertEquals(1, drugsRecordRepository.countRecord());
//
//        drugsRecordRepository.deleteRecord(101);
//
//        assertEquals(0, drugsRecordRepository.countRecord());
//    }
//
//    @Test
//    public void testThatUserCanDeleteSalesFromRecord_UserTriesToDeleteSalesThatIsNotInTheRecord(){
//
//        DispenseDrug dispenseDrug = new DispenseDrug();
//        dispenseDrug.setBatchId("2026");
//        dispenseDrug.setId(101);
//        dispenseDrug.setDrug("Emzor Paracetamol");
//        dispenseDrug.setQuantity(5);
//
//        ArrayList <DispenseDrug> drugs = new ArrayList<>();
//        drugs.add(dispenseDrug);
//
//        ArrayList <String> drugg = new ArrayList<>();
//        drugg.add("Emzor Paracetamol");
//
//        DispensedDrugsRecordRepositoryImpl drugsRecordRepository = new DispensedDrugsRecordRepositoryImpl();
//        DispensedDrugsRecord record = new DispensedDrugsRecord();
//        record.setAmount(BigDecimal.valueOf(500));
//        record.setDrug(drugg);
//        record.setUser("miracle");
//        record.setDateAndTime(LocalDateTime.now());
//        record.setQuantitySold(1);
//        record.setSaleId(101);
//        drugsRecordRepository.addCurrentSales(record);
//
//        assertEquals(1, drugsRecordRepository.countRecord());
//
//        assertThrows(SalesRecordNotFoundException.class, () -> drugsRecordRepository.deleteRecord(100));
//    }
//
//    @Test
//    public void testThatUserCanDeleteAllSalesFromRecord(){
//
//        DispenseDrug dispenseDrug = new DispenseDrug();
//        dispenseDrug.setBatchId("2026");
//        dispenseDrug.setId(101);
//        dispenseDrug.setDrug("Emzor Paracetamol");
//        dispenseDrug.setQuantity(5);
//
//        ArrayList <DispenseDrug> drugs = new ArrayList<>();
//        drugs.add(dispenseDrug);
//
//        ArrayList <String> drugg = new ArrayList<>();
//        drugg.add("Emzor Paracetamol");
//
//        DispensedDrugsRecordRepositoryImpl drugsRecordRepository = new DispensedDrugsRecordRepositoryImpl();
//        DispensedDrugsRecord record = new DispensedDrugsRecord();
//        record.setAmount(BigDecimal.valueOf(500));
//        record.setDrug(drugg);
//        record.setUser("miracle");
//        record.setDateAndTime(LocalDateTime.now());
//        record.setQuantitySold(1);
//        record.setSaleId(101);
//        drugsRecordRepository.addCurrentSales(record);
//
//        assertEquals(1, drugsRecordRepository.countRecord());
//
//        drugsRecordRepository.deleteAll();
//
//        assertEquals(0, drugsRecordRepository.countRecord());
//    }
//
//}
