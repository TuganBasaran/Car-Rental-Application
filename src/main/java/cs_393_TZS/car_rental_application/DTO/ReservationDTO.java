package cs_393_TZS.car_rental_application.DTO;

import java.time.LocalDateTime;

public class ReservationDTO {
    private String reservationNumber;
    private String carLicensePlate;
    private String memberName;
    private String status;
    private LocalDateTime dropOffDate;
    private LocalDateTime returnDate;
    private LocalDateTime creationDate;
    private LocalDateTime pickUpDate;
    private String pickUpLocationName;
    private String dropOffLocationName;


    public ReservationDTO() {}

    public ReservationDTO(String reservationNumber, LocalDateTime creationDate, LocalDateTime pickUpDate,
                          LocalDateTime dropOffDate, LocalDateTime returnDate, String pickUpLocationName,
                          String dropOffLocationName, String carLicensePlate, String memberName, String status) {
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
    }





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

    public LocalDateTime getDropOffDate() {
        return dropOffDate;
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


    public void setPickUpDate(LocalDateTime pickUpDate) {
        this.pickUpDate = pickUpDate;
    }


    public void setDropOffDate(LocalDateTime dropOffDate) {
        this.dropOffDate = dropOffDate;
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
}
