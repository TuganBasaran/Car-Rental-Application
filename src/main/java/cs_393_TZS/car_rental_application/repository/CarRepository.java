package cs_393_TZS.car_rental_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cs_393_TZS.car_rental_application.model.Car;
import cs_393_TZS.car_rental_application.model.CarStatus;

import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long>{
    List<Car> findByStatus(CarStatus status);
}


