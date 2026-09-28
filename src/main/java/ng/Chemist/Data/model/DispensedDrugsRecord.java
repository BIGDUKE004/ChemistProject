package ng.Chemist.Data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Document
public class DispensedDrugsRecord {
    @Id
    private int saleId;

    private LocalDateTime dateAndTime;
    private String user;
    private ArrayList <String> drugs = new ArrayList<>();
    private int quantitySold;
    private BigDecimal amount;

}
