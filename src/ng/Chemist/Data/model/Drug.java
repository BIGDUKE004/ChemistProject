package ng.Chemist.Data.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Drug {
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


    public void setId(int id){
        this.id = id;
    }

    public void setBrandName(String brandName){
        this.brandName = brandName.toLowerCase();
    }

    public void setGenericName(String genericName){
        this.genericName = genericName.toLowerCase();
    }

    public void setStrength(String strength){
        this.strength = strength.toLowerCase();
    }

    public void setDosage(String dosage){
        this.dosage = dosage.toLowerCase();
    }

    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer.toLowerCase();
    }

    public void setBatchNumber(String batchNumber){
        this.batchNumber = batchNumber;
    }

    public void setManufactureDate(LocalDate manufactureDate){
        this.manufactureDate = manufactureDate;
    }

    public void setExpiryDate(LocalDate expiryDate){
        this.expiryDate = expiryDate;
    }

    public void setPrice(int price){
        this.price = BigDecimal.valueOf(price);
    }

    public void setQuantityInStock(int quantityInStock){
        this.quantityInStock = quantityInStock;
    }

    public int getId() {
        return this.id;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public String getGenericName() {
        return this.genericName;
    }

    public String getStrength() {
        return this.strength;
    }

    public String getDosage() {
        return this.dosage;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getBatchNumber() {
        return this.batchNumber;
    }

    public LocalDate getManufactureDate() {
        return this.manufactureDate;
    }

    public LocalDate getExpiryDate() {
        return this.expiryDate;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public int getQuantityInStock() {
        return this.quantityInStock;
    }

    public String toString () {
        String message =  "Medicine ID: " + id + "\n" +
                "Brand Name: " + brandName + "\n" +
                "Generic Name: " + genericName + "\n" +
                "Strength: " + strength + "\n" +
                "Dosage Form: " + dosage + "\n" +
                "Manufacturer: " + manufacturer + "\n" +
                "Batch Number: " + batchNumber + "\n" +
                "Manufacture Date: " + manufactureDate + "\n" +
                "Expiry Date: " + expiryDate + "\n" +
                "Unit Price: " + price.intValue() + "\n" +
                "Quantity in Stock: " + quantityInStock;
        return message.trim();
    }
}
