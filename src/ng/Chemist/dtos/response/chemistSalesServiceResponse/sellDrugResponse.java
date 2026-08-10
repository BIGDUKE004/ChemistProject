package ng.Chemist.dtos.response.chemistSalesServiceResponse;

import ng.Chemist.Data.model.DispenseDrug;
import ng.Chemist.Data.model.DispensedDrugsRecord;

import java.math.BigDecimal;

public class sellDrugResponse {
    private DispensedDrugsRecord record = new DispensedDrugsRecord();

    public String messageToString() {
        String message = "Total Amount: ₦" + record.getAmount() + "\n" +
                "Quantity Sold: " + record.getQuantitySold() + "\n" +
                "Drugs: " + record.getDispensedDrugs();
        return message;
    }

    public void setMessage(DispensedDrugsRecord record) {
        this.record = record;
    }
}
