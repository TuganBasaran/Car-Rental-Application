package cs_393_TZS.car_rental_application.repository;

import cs_393_TZS.car_rental_application.model.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import cs_393_TZS.car_rental_application.model.Car;
import cs_393_TZS.car_rental_application.model.CarStatus;

import java.util.List;
import java.util.Optional;


public interface CarRepository extends JpaRepository<Car, Long>{
    List<Car> findByStatus(CarStatus status);
    Optional<Car> findByBarcode(Long barcode);
    Car findCarByLicensePlate(String licensePlate);
    List<Car> findCarByTypeAndTransmissionType(CarType type, String transmissionType);

}


