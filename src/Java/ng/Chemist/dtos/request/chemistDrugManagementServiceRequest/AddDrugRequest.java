package Java.ng.Chemist.dtos.request.chemistDrugManagementServiceRequest;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class AddDrugRequest {
    private int id;
    private String userId;
    private String brandName;
    private String genericName;
    private String strength;
    private String dosage;
    private String manufacturer;
    private String batchNumber;
    private LocalDate manufactureDate;
    private LocalDate expiryDate;
    private int price;
    private int quantityInStock;

}
