package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.DTO.CarDTO;
import cs_393_TZS.car_rental_application.model.Car;
import cs_393_TZS.car_rental_application.model.CarStatus;
import cs_393_TZS.car_rental_application.model.CarType;
import cs_393_TZS.car_rental_application.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarServiceTest {

    @Autowired
    private CarService carService;

    //Test Object: Car
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

    Car carObject = new Car(barcode, licensePlate, passengerCapacity, brand,
            model, mileage, transmissionType, dailyPrice , carType, carStatus);

    @AfterEach
    void deleteAll(){
        carService.deleteAll();
    }

    @Test
    void toCarDTO() {
        CarDTO testObjectDTO = carService.toCarDTO(carObject);
        assertEquals(carObject.getLicensePlate(), testObjectDTO.getLicensePlate());
    }

    @Test
    void toCar() {
        CarDTO testObjectDTO = carService.toCarDTO(carObject);
        Car revertedCar = carService.toCar(testObjectDTO);
        assertEquals(carObject.getBarcode(), revertedCar.getBarcode());
    }


    @Test
    void saveCarDTOAndFindByStatus() {
        CarDTO testObjectDTO = carService.toCarDTO(carObject);
        CarDTO savedCarDTO = carService.saveCarDTO(testObjectDTO);
        List<CarDTO> foundCar = carService.findCarsByStatus(CarStatus.AVAILABLE);
        assertEquals(savedCarDTO.getBarcode(), foundCar.getFirst().getBarcode());
    }

    @Test
    void saveCarDTOAndFindByBarcode() {
        CarDTO testObjectDTO = carService.toCarDTO(carObject);
        CarDTO savedCarDTO = carService.saveCarDTO(testObjectDTO);
        List<CarDTO> foundCar = carService.findCarsByBarcode(savedCarDTO.getBarcode());
        assertEquals(savedCarDTO.getBarcode(), foundCar.getFirst().getBarcode());
    }

    @Test
    void deleteCar() {
        CarDTO testObjectDTO = carService.toCarDTO(carObject);
        CarDTO savedCarDTO = carService.saveCarDTO(testObjectDTO);
        carService.deleteCar(savedCarDTO.getBarcode());
        List<CarDTO> foundCar = carService.findCarsByBarcode(savedCarDTO.getBarcode());
        assertTrue(foundCar.isEmpty());
    }

}
