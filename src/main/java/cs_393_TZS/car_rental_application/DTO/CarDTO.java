package cs_393_TZS.car_rental_application.DTO;

public class CarDTO {
    private String brand;
    private String model;
    private String type; // Car type as a string
    private double mileage;
    private String transmissionType;
    private Long barcode;

    // Constructor
    public CarDTO(String brand, String model, String type, double mileage, String transmissionType, Long barcode) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.mileage = mileage;
        this.transmissionType = transmissionType;
        this.barcode = barcode;
    }

    // Getters and Setters
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type; // Ensure this getter exists
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }
}
