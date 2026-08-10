package ng.chemist.repositoryTest;

import ng.Chemist.Data.model.Drug;
import ng.Chemist.Data.repositories.DrugRepository;
import ng.Chemist.Data.repositories.DrugRepositoryImpl;
import ng.Chemist.exceptions.repositoriesException.DrugDoesNotExistException;
import ng.Chemist.exceptions.repositoriesException.SameIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DrugRepositoryImplTest {

    private DrugRepository drugRepository;
    @BeforeEach
    public void setUp(){
        drugRepository = new DrugRepositoryImpl();
    }

    @Test
    public void testThatMethodAddDrugsWorks(){
        Drug drug = new Drug();
        drug.setBatchNumber("EMP2026001");
        drug.setBrandName("Emzor Paracetamol");
        drug.setDosage("Tablet");
        drug.setStrength("500mg");
        drug.setQuantityInStock(100);
        drug.setPrice(500);
        drug.setId(101);
        drug.setGenericName("paracetamol");
        drug.setManufacturer("Emzor");
        drug.setManufactureDate(LocalDate.of(2026,1,10));
        drug.setExpiryDate(LocalDate.of(2028, 1, 10));
        Drug check = drugRepository.add(drug);
        assertEquals(1, drugRepository.count());
        assertEquals("EMP2026001", check.getBatchNumber());
    }

    @Test
    public void aDrugHaveBeenAdded_TheDrugNameHasBeenUpdated(){
        Drug drug = new Drug();
        drug.setBatchNumber("EMP2026001");
        drug.setBrandName("Emzor Paracetamol");
        drug.setDosage("Tablet");
        drug.setStrength("500mg");
        drug.setQuantityInStock(100);
        drug.setPrice(500);
        drug.setId(101);
        drug.setGenericName("paracetamol");
        drug.setManufacturer("Emzor");
        drug.setManufactureDate(LocalDate.of(2026,1,10));
        drug.setExpiryDate(LocalDate.of(2028, 1, 10));
        Drug check = drugRepository.add(drug);
        assertEquals(1, drugRepository.count());
        assertEquals("EMP2026001", check.getBatchNumber());

        Drug updatedDrug = new Drug();
        updatedDrug.setBatchNumber("EMP2026001");
        updatedDrug.setBrandName("Emzor");
        updatedDrug.setDosage("Tablet");
        updatedDrug.setStrength("500mg");
        updatedDrug.setQuantityInStock(100);
        updatedDrug.setPrice(500);
        updatedDrug.setId(101);
        updatedDrug.setGenericName("paracetamol");
        updatedDrug.setManufacturer("Emzor");
        updatedDrug.setManufactureDate(LocalDate.of(2026,1,10));
        updatedDrug.setExpiryDate(LocalDate.of(2028, 1, 10));
        drugRepository.updateDrug(updatedDrug, 101);
        assertEquals(1, drugRepository.count());
        assertEquals("emzor", drugRepository.findById(101).getBrandName());
    }

    @Test
    public void testThatUserDecidedToDeleteADrug(){
        Drug drug = new Drug();
        drug.setBatchNumber("EMP2026001");
        drug.setBrandName("Emzor Paracetamol");
        drug.setDosage("Tablet");
        drug.setStrength("500mg");
        drug.setQuantityInStock(100);
        drug.setPrice(500);
        drug.setId(101);
        drug.setGenericName("paracetamol");
        drug.setManufacturer("Emzor");
        drug.setManufactureDate(LocalDate.of(2026,1,10));
        drug.setExpiryDate(LocalDate.of(2028, 1, 10));
        Drug check = drugRepository.add(drug);
        assertEquals(1, drugRepository.count());
        assertEquals("EMP2026001", check.getBatchNumber());

        drugRepository.delete(101);
        assertEquals(0, drugRepository.count());
    }

    @Test
    public void testThatUserHaveTwoDrugs_UserDeletesAll_CountIsZero(){
        Drug drug = new Drug();
        drug.setBatchNumber("EMP2026001");
        drug.setBrandName("Emzor Paracetamol");
        drug.setDosage("Tablet");
        drug.setStrength("500mg");
        drug.setQuantityInStock(100);
        drug.setPrice(500);
        drug.setId(100);
        drug.setGenericName("paracetamol");
        drug.setManufacturer("Emzor");
        drug.setManufactureDate(LocalDate.of(2026,1,10));
        drug.setExpiryDate(LocalDate.of(2028, 1, 10));
        Drug check = drugRepository.add(drug);
        assertEquals(1, drugRepository.count());
        assertEquals("EMP2026001", check.getBatchNumber());


        Drug secDrug = new Drug();
        secDrug.setBatchNumber("EMP2027001");
        secDrug.setBrandName("Emzor");
        secDrug.setDosage("Tabet");
        secDrug.setStrength("500");
        secDrug.setQuantityInStock(100);
        secDrug.setPrice(500);
        secDrug.setId(101);
        secDrug.setGenericName("paracetamol");
        secDrug.setManufacturer("Emzor");
        secDrug.setManufactureDate(LocalDate.of(2026,1,10));
        secDrug.setExpiryDate(LocalDate.of(2028, 1, 10));
        Drug secCheck = drugRepository.add(secDrug);
        assertEquals(2, drugRepository.count());
        assertEquals("EMP2027001", secCheck.getBatchNumber());

        drugRepository.deleteAll(true);
        assertEquals(0, drugRepository.count());
    }

    @Test
    public void testThatUserSearchForADrugUsingItName(){
        Drug drug = new Drug();
        drug.setBatchNumber("EMP2026001");
        drug.setBrandName("Emzor Paracetamol");
        drug.setDosage("Tablet");
        drug.setStrength("500mg");
        drug.setQuantityInStock(100);
        drug.setPrice(500);
        drug.setId(100);
        drug.setGenericName("paracetamol");
        drug.setManufacturer("Emzor");
        drug.setManufactureDate(LocalDate.of(2026,1,10));
        drug.setExpiryDate(LocalDate.of(2028, 1, 10));
        Drug check = drugRepository.add(drug);
        assertEquals(1, drugRepository.count());
        assertEquals("EMP2026001", check.getBatchNumber());


        Drug secDrug = new Drug();
        secDrug.setBatchNumber("EMP2027001");
        secDrug.setBrandName("Emzor");
        secDrug.setDosage("Tabet");
        secDrug.setStrength("500");
        secDrug.setQuantityInStock(100);
        secDrug.setPrice(500);
        secDrug.setId(101);
        secDrug.setGenericName("paracetamol");
        secDrug.setManufacturer("Emzor");
        secDrug.setManufactureDate(LocalDate.of(2026,1,10));
        secDrug.setExpiryDate(LocalDate.of(2028, 1, 10));
        Drug secCheck = drugRepository.add(secDrug);
        assertEquals(2, drugRepository.count());
        assertEquals("EMP2027001", secCheck.getBatchNumber());

        Drug searchedDrug = drugRepository.SearchByName("Emzor");
        assertSame(secDrug.getId(), searchedDrug.getId());
    }

    @Test
    public void testThatUserSearchForADrugUsingItId(){
        Drug drug = new Drug();
        drug.setBatchNumber("EMP2026001");
        drug.setBrandName("Emzor Paracetamol");
        drug.setDosage("Tablet");
        drug.setStrength("500mg");
        drug.setQuantityInStock(100);
        drug.setPrice(500);
        drug.setId(100);
        drug.setGenericName("paracetamol");
        drug.setManufacturer("Emzor");
        drug.setManufactureDate(LocalDate.of(2026,1,10));
        drug.setExpiryDate(LocalDate.of(2028, 1, 10));
        Drug check = drugRepository.add(drug);
        assertEquals(1, drugRepository.count());
        assertEquals("EMP2026001", check.getBatchNumber());


        Drug secDrug = new Drug();
        secDrug.setBatchNumber("EMP2027001");
        secDrug.setBrandName("Emzor");
        secDrug.setDosage("Tabet");
        secDrug.setStrength("500");
        secDrug.setQuantityInStock(100);
        secDrug.setPrice(500);
        secDrug.setId(101);
        secDrug.setGenericName("paracetamol");
        secDrug.setManufacturer("Emzor");
        secDrug.setManufactureDate(LocalDate.of(2026,1,10));
        secDrug.setExpiryDate(LocalDate.of(2028, 1, 10));
        Drug secCheck = drugRepository.add(secDrug);
        assertEquals(2, drugRepository.count());
        assertEquals("EMP2027001", secCheck.getBatchNumber());

        Drug searchedDrug = drugRepository.findById(101);
        assertSame(secDrug.getId(), searchedDrug.getId());
    }

    @Test
    public void testThatUserSearchesForDrugThatDoesNotExistByName(){
        assertThrows(DrugDoesNotExistException.class, () ->
                drugRepository.SearchByName("paracetamol"));
    }

    @Test
    public void testThatUserSearchesForDrugThatDoesNotExistUsingId(){
        assertThrows(DrugDoesNotExistException.class, () ->
                drugRepository.findById(101));
    }

    @Test
    public void testThatGetInformationsAboutADrugBySearchingForItUsingItName(){
        Drug drug = new Drug();
        drug.setBatchNumber("EMP2026001");
        drug.setBrandName("Emzor Paracetamol");
        drug.setDosage("Tablet");
        drug.setStrength("500mg");
        drug.setQuantityInStock(100);
        drug.setPrice(500);
        drug.setId(100);
        drug.setGenericName("paracetamol");
        drug.setManufacturer("Emzor");
        drug.setManufactureDate(LocalDate.of(2026,1,10));
        drug.setExpiryDate(LocalDate.of(2028, 1, 10));
        Drug check = drugRepository.add(drug);
        assertEquals(1, drugRepository.count());
        assertEquals("EMP2026001", check.getBatchNumber());

        Drug searchedDrug = drugRepository.viewDrugInformation("Emzor Paracetamol");
        assertSame(drug, searchedDrug);
    }

    @Test
    public void testThatUserCannotAddAnotherDrugWithTheSameId(){
        Drug drug = new Drug();
        drug.setBatchNumber("EMP2026001");
        drug.setBrandName("Emzor Paracetamol");
        drug.setDosage("Tablet");
        drug.setStrength("500mg");
        drug.setQuantityInStock(100);
        drug.setPrice(500);
        drug.setId(101);
        drug.setGenericName("paracetamol");
        drug.setManufacturer("Emzor");
        drug.setManufactureDate(LocalDate.of(2026,1,10));
        drug.setExpiryDate(LocalDate.of(2028, 1, 10));
        Drug check = drugRepository.add(drug);
        assertEquals(1, drugRepository.count());
        assertEquals("EMP2026001", check.getBatchNumber());


        Drug secDrug = new Drug();
        secDrug.setBatchNumber("EMP2027001");
        secDrug.setBrandName("Emzor");
        secDrug.setDosage("Tabet");
        secDrug.setStrength("500");
        secDrug.setQuantityInStock(100);
        secDrug.setPrice(500);
        secDrug.setId(101);
        secDrug.setGenericName("paracetamol");
        secDrug.setManufacturer("Emzor");
        secDrug.setManufactureDate(LocalDate.of(2026,1,10));
        secDrug.setExpiryDate(LocalDate.of(2028, 1, 10));
        assertThrows(SameIdException.class, () -> drugRepository.add(secDrug));
    }

    @Test
    public void testThatUserSearchesForDrugInformationThatDoesNotExistInTheSystem(){
        assertThrows(DrugDoesNotExistException.class, () -> drugRepository.viewDrugInformation("paracetamols"));
    }
}
