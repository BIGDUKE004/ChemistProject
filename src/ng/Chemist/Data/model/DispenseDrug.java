package ng.Chemist.Data.model;
public class DispenseDrug {
    private String dosage;
    private String batchId;
    private String drugName;
    private int quantity;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDrug(String drug) {
        this.drugName = drug;
    }

    public void setId(String dosage){this.dosage = dosage;}

    public void setBatchId(String batchId){this.batchId = batchId;}

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDosage(String dosage){this.dosage = dosage;}

    public String getDrug() {
        return drugName;
    }

    public String getDosage() {
        return dosage;
    }

    public String getBatchId() {
        return batchId;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
