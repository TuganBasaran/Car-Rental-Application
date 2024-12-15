package cs_393_TZS.car_rental_application.repository;

import cs_393_TZS.car_rental_application.model.Car;
import cs_393_TZS.car_rental_application.model.Reservation;
import cs_393_TZS.car_rental_application.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository <Reservation, String>{
    List<Reservation> findByStatus(ReservationStatus status);
    boolean existsByCar(Car car);
}
