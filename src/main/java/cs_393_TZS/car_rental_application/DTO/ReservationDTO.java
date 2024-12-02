package cs_393_TZS.car_rental_application.DTO;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationDTO {

    private String reservationNumber;
    private LocalDateTime creationDate;
    private LocalDateTime pickUpDate;
    private LocalDateTime dropOffDate;
    private LocalDateTime returnDate;
    private String pickUpLocationName;
    private String dropOffLocationName;
    private String carLicensePlate;
    private String memberName;
    private String status;
    private double totalCost;
    private List<String> equipmentNames;
    private List<String> serviceNames;

    // Constructor
    public ReservationDTO(String reservationNumber, LocalDateTime creationDate, LocalDateTime pickUpDate,
                          LocalDateTime dropOffDate, LocalDateTime returnDate, String pickUpLocationName,
                          String dropOffLocationName, String carLicensePlate, String memberName,
                          String status, double totalCost, List<String> equipmentNames, List<String> serviceNames) {
        this.reservationNumber = reservationNumber;
        this.creationDate = creationDate;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.returnDate = returnDate;
        this.pickUpLocationName = pickUpLocationName;
        this.dropOffLocationName = dropOffLocationName;
        this.carLicensePlate = carLicensePlate;
        this.memberName = memberName;
        this.status = status;
        this.totalCost = totalCost;
        this.equipmentNames = equipmentNames;
        this.serviceNames = serviceNames;
    }

    // Getters and Setters
    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDateTime pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDateTime getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(LocalDateTime dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public String getPickUpLocationName() {
        return pickUpLocationName;
    }

    public void setPickUpLocationName(String pickUpLocationName) {
        this.pickUpLocationName = pickUpLocationName;
    }

    public String getDropOffLocationName() {
        return dropOffLocationName;
    }

    public void setDropOffLocationName(String dropOffLocationName) {
        this.dropOffLocationName = dropOffLocationName;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public List<String> getEquipmentNames() {
        return equipmentNames;
    }

    public void setEquipmentNames(List<String> equipmentNames) {
        this.equipmentNames = equipmentNames;
    }

    public List<String> getServiceNames() {
        return serviceNames;
    }

    public void setServiceNames(List<String> serviceNames) {
        this.serviceNames = serviceNames;
    }
}