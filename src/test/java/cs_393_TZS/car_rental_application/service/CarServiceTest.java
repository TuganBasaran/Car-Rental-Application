package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.DTO.CarDTO;
import cs_393_TZS.car_rental_application.model.Car;
import cs_393_TZS.car_rental_application.model.CarStatus;
import cs_393_TZS.car_rental_application.model.CarType;
import cs_393_TZS.car_rental_application.service.CarService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarServiceTest {

    @Autowired
    private CarService carService;

    @AfterEach
    void deleteAll() {
        carService.deleteAll();
        List<CarDTO> allCars = carService.findCarsByStatus(CarStatus.AVAILABLE);
        assertTrue(allCars.isEmpty(), "Database is not empty after deleteAll!");
    }

    private Car createTestCar() {
        return new Car(1L, "A1B2C3",
                4, "Toyota", "Corolla",
                123.2, "Manual", 112.2,
                CarType.STANDARD, CarStatus.AVAILABLE);
    }

    @Test
    void toCarDTO() {
        Car car = createTestCar();
        CarDTO carDTO = carService.toCarDTO(car);
        assertEquals(car.getLicensePlate(), carDTO.getLicensePlate());
        assertEquals(car.getBrand(), carDTO.getBrand());
        assertEquals(car.getStatus(), carDTO.getStatus());
    }

    @Test
    void toCar() {
        Car car = createTestCar();
        CarDTO carDTO = carService.toCarDTO(car);
        Car revertedCar = carService.toCar(carDTO);
        assertEquals(car.getBarcode(), revertedCar.getBarcode());
        assertEquals(car.getBrand(), revertedCar.getBrand());
    }

    @Test
    void saveCarDTOAndFindByStatus() {
        Car car = createTestCar();
        CarDTO carDTO = carService.toCarDTO(car);
        carService.saveCarDTO(carDTO);
        List<CarDTO> foundCars = carService.findCarsByStatus(CarStatus.AVAILABLE);
        assertFalse(foundCars.isEmpty());
        assertEquals(carDTO.getBarcode(), foundCars.get(0).getBarcode());
    }

    @Test
    void saveCarDTOAndFindByBarcode() {
        Car car = createTestCar();
        CarDTO carDTO = carService.toCarDTO(car);
        CarDTO savedCar = carService.saveCarDTO(carDTO);
        List<CarDTO> foundCars = carService.findCarsByBarcode(savedCar.getBarcode());

        // Liste boş değil
        assertFalse(foundCars.isEmpty(), "No cars found with the given barcode!");

        // Liste sadece bir eleman içermeli
        assertEquals(1, foundCars.size(), "Expected exactly one car but found " + foundCars.size());

        // İlk elemanın barcode'u doğrulanıyor
        assertEquals(savedCar.getBarcode(), foundCars.get(0).getBarcode());
    }

    @Test
    void deleteCar() {
        Car car = createTestCar();
        CarDTO carDTO = carService.toCarDTO(car);
        CarDTO savedCarDTO = carService.saveCarDTO(carDTO);
        carService.deleteCar(savedCarDTO.getBarcode());
        List<CarDTO> foundCars = carService.findCarsByBarcode(carDTO.getBarcode());
        assertTrue(foundCars.isEmpty());
    }
}