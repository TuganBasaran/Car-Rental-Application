package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;


}
