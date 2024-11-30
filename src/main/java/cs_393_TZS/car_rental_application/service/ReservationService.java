package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.model.*;
import cs_393_TZS.car_rental_application.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;


    //saving the reservation
    Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    //checking if the car is available
    private void checkCarAvailability(Car car) {
        if (!car.getStatus().equals(CarStatus.AVAILABLE)) {
            throw new IllegalArgumentException("Car you are choosing is not available");
        }
    }

    //checking the dates
    private void checkReservationDates(LocalDateTime pickUpDate, LocalDateTime dropOffDate) {
        if (pickUpDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Pick up date cannot be in the past");
        }
        if (pickUpDate.isAfter(dropOffDate)) {
            throw new IllegalArgumentException("Pick up date cannot be after drop-off date");
        }
        if (dropOffDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Drop off date cannot be in the past");
        }
    }

    //reservation in the making
    private Reservation generateReservation(Member member, Car car, Location pickUpLocation, Location dropOffLocation,
                                         LocalDateTime pickUpDate, LocalDateTime dropOffDate) {
        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setMember(member);
        reservation.setPickUpLocation(pickUpLocation);
        reservation.setDropOffLocation(dropOffLocation);
        reservation.setStatus(ReservationStatus.PENDING); // Rezervasyon başlangıç durumu
        reservation.setReservationNumber(reservation.generateReservationNumber());
        reservation.setPickUpDate(pickUpDate);
        reservation.setDropOffDate(dropOffDate);
        reservation.setCreationDate(LocalDateTime.now());
        return reservation;
    }

    //create a reservation
    public Reservation createReservation(Member member, Car car, Location pickUpLocation, Location dropOffLocation,
                                         LocalDateTime pickUpDate, LocalDateTime dropOffDate) {
        checkCarAvailability(car);
        checkReservationDates(pickUpDate, dropOffDate);

        Reservation reservation = generateReservation(member, car, pickUpLocation, dropOffLocation, pickUpDate, dropOffDate);

        car.setStatus(CarStatus.RESERVED);
        return reservationRepository.save(reservation);
    }


    //if the reservation is loaned
    public void markCarAsLoaned(String reservationNumber) {
        Reservation reservation = reservationRepository.findById(reservationNumber)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        reservation.getCar().setStatus(CarStatus.LOANED);
        reservation.setStatus(ReservationStatus.ACTIVE);
        reservationRepository.save(reservation);
    }

}
