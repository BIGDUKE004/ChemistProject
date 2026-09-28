package Java.ng.Chemist.Data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class DispenseDrug {
    private String dosage;
    private String batchId;
    private String drugName;
    private int quantity;

    @Id
    private int id;


}
