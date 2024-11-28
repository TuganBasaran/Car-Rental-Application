package cs_393_TZS.car_rental_application;

import cs_393_TZS.car_rental_application.model.Car;
import cs_393_TZS.car_rental_application.model.CarStatus;
import cs_393_TZS.car_rental_application.model.CarType;
import cs_393_TZS.car_rental_application.model.Reservation;
import cs_393_TZS.car_rental_application.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    void testFindByStatus_ShouldReturnCars() {
        Long barcode = 1L;
        String licensePlate = "A1B2C3";
        int passengerCapacity = 4;
        String brand = "Toyota";
        String model = "Corolla";
        double mileage = 123.2;
        String transmissionType = "Manual";
        double dailyPrice = 112.2;
        CarType carType = CarType.STANDARD;
        CarStatus carStatus = CarStatus.AVAILABLE;
        Reservation reservation = new Reservation();

        Car car = new Car(barcode, licensePlate, passengerCapacity, brand,
                model, mileage, transmissionType, dailyPrice, carType, carStatus);

        Car savedCar = carRepository.save(car);

        List<Car> foundCar = carRepository.findByStatus(CarStatus.AVAILABLE);
        assertEquals(savedCar.getStatus(), foundCar.getFirst().getStatus());

    }

    @Test
    void testFindByStatus_ShouldReturnEmptyList() {
        // Act
        List<Car> cars = carRepository.findByStatus(CarStatus.RESERVED);

        // Assert
        assertTrue(cars.isEmpty());
    }
}
