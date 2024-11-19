package cs_393_TZS.car_rental_application.service.impl;

import cs_393_TZS.car_rental_application.model.Reservation;
import cs_393_TZS.car_rental_application.repository.ReservationRepository;
import cs_393_TZS.car_rental_application.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        return null;
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {
        return null;
    }

    @Override
    public Reservation getReservationById(Long id) {
        return null;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return List.of();
    }

    @Override
    public void cancelReservation(Long id) {

    }
}
