package ng.Chemist.Data.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DispensedDrugsRecord {
    private int saleId;
    private LocalDateTime dateAndTime;
    private String user;
    private ArrayList <String> drugs = new ArrayList<>();
    private int quantitySold;
    private BigDecimal amount;



    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getQuantitySold() {
        return this.quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public ArrayList<String> getDispensedDrugs() {
        return this.drugs;
    }

    public void setDrug(ArrayList<String> dispensedDrugs) {
        for(int count = 0; count < dispensedDrugs.size(); count++){
            this.drugs.add(dispensedDrugs.get(count));
        }
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
