package cs_393_TZS.car_rental_application.repository;

import cs_393_TZS.car_rental_application.model.Car;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByCarTypeAndTransmissionTypeAndStatus(String carType, String transmissionType, String status);
    
}