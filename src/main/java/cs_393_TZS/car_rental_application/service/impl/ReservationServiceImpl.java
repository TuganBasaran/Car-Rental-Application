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

}
