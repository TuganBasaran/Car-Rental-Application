package cs_393_TZS.car_rental_application.DTO;

import cs_393_TZS.car_rental_application.model.CarStatus;
import cs_393_TZS.car_rental_application.model.CarType;

public class CarDTO {
    private String brand;
    private String model;
    private CarType type; // Car type as a string
    private double mileage;
    private String transmissionType;
    private Long barcode;
    private String licensePlate;
    private CarStatus status;

    // Constructor
    public CarDTO(String brand, String model, CarType type, double mileage, String transmissionType, Long barcode, String licensePlate, CarStatus status) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.mileage = mileage;
        this.transmissionType = transmissionType;
        this.barcode = barcode;
        this.licensePlate = licensePlate;
        this.status = status;
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

    public CarType getType() {
        return type; // Ensure this getter exists
    }

    public void setType(CarType type) {
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

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }
}
