package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.model.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation createReservation(Reservation reservation); // Create a new reservation
    Reservation updateReservation(Long id, Reservation reservation); // Update reservation details
    Reservation getReservationById(Long id); // Fetch reservation by ID
    List<Reservation> getAllReservations(); // List all reservations
    void cancelReservation(Long id); // Cancel reservation by ID
}
