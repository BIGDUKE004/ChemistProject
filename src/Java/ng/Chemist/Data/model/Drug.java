package Java.ng.Chemist.Data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Document
public class Drug {
    @Id
    private int id;

    private String brandName;
    private String genericName;
    private String strength;
    private String dosage;
    private String manufacturer;
    private String batchNumber;
    private LocalDate manufactureDate;
    private LocalDate expiryDate;
    private BigDecimal price;
    private int quantityInStock;


}
