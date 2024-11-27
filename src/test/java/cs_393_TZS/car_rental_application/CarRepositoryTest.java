package cs_393_TZS.car_rental_application;

import cs_393_TZS.car_rental_application.model.Car;
import cs_393_TZS.car_rental_application.model.CarStatus;
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
        // Arrange
        Car car = new Car();
        car.setStatus(CarStatus.AVAILABLE);
        carRepository.save(car);

        // Act
        List<Car> cars = carRepository.findByStatus(CarStatus.AVAILABLE);

        // Assert
        assertFalse(cars.isEmpty());
        assertEquals(1, cars.size());
        assertEquals(CarStatus.AVAILABLE, cars.get(0).getStatus());
    }

    @Test
    void testFindByStatus_ShouldReturnEmptyList() {
        // Act
        List<Car> cars = carRepository.findByStatus(CarStatus.RESERVED);

        // Assert
        assertTrue(cars.isEmpty());
    }
}
