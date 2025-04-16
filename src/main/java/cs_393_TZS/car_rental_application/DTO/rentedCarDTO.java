package cs_393_TZS.car_rental_application.DTO;

import cs_393_TZS.car_rental_application.model.CarType;

import java.time.LocalDateTime;

public class rentedCarDTO {
    private String brand;
    private String model;
    private CarType type;
    private String transmissionType;
    private Long carBarcode;
    private String reservationNumber;
    private String memberName;
    private LocalDateTime dropOffDateTime;
    private Long dropOffLocation;
    private int reservationDayCount;

    public rentedCarDTO(String brand, String model, CarType type, String transmissionType, Long carBarcode, String reservationNumber, String memberName, LocalDateTime dropOffDateTime, Long dropOffLocation, int reservationDayCount) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.transmissionType = transmissionType;
        this.carBarcode = carBarcode;
        this.reservationNumber = reservationNumber;
        this.memberName = memberName;
        this.dropOffDateTime = dropOffDateTime;
        this.dropOffLocation = dropOffLocation;
        this.reservationDayCount = reservationDayCount;
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

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Long getCarBarcode() {
        return carBarcode;
    }

    public void setCarBarcode(Long carBarcode) {
        this.carBarcode = carBarcode;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public LocalDateTime getDropOffDateTime() {
        return dropOffDateTime;
    }

    public void setDropOffDateTime(LocalDateTime dropOffDateTime) {
        this.dropOffDateTime = dropOffDateTime;
    }

    public Long getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(Long dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public int getReservationDayCount() {
        return reservationDayCount;
    }

    public void setReservationDayCount(int reservationDayCount) {
        this.reservationDayCount = reservationDayCount;
    }
}
