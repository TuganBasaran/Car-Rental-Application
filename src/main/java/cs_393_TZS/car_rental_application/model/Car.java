package cs_393_TZS.car_rental_application.model;

import jakarta.persistence.*;

import java.util.Objects;

/*
The basic building block of the system. Every car will have a barcode, license plate
number, passenger capacity, brand, model, mileage, transmission type, dailyPrice
etc. Cars can be of multiple types like:
• Economy
• People Carrier
• Estate
• SUV
• Standard
• Convertible
• Luxury
A car also can have different status:
• AVAILABLE
• RESERVED
• LOANED
• LOST
• BEING_SERVICED
*/

@Entity
public class Car {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String barcode;

    @Column(nullable = false)
    private String licensePlate;

    private int passengerCapacity;
    private String brand;
    private String model;
    private double mileage;
    private String transmissionType;
    private double dailyPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarType type; // Enum for car types

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarStatus status; // Enum for car statuses

    public Car() {
    }

    public Car(String barcode, String licensePlate, int passengerCapacity, String brand, String model, double mileage, String transmissionType, double dailyPrice, CarType type, CarStatus status) {
        this.barcode = barcode;
        this.licensePlate = licensePlate;
        this.passengerCapacity = passengerCapacity;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.transmissionType = transmissionType;
        this.dailyPrice = dailyPrice;
        this.type = type;
        this.status = status;
    }

    //GETTER AND SETTERS


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

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

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

}