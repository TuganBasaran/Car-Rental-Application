package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.model.Car;
import cs_393_TZS.car_rental_application.model.CarStatus;
import cs_393_TZS.car_rental_application.model.CarType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    Car carObject = new Car(barcode, licensePlate, passengerCapacity, brand, model, mileage, transmissionType, dailyPrice , carType, carStatus);



    @Test
    void testSaveCarAndFindByBarcode() {
        Car savedCar = carService.saveCar(carObject);
        List<Car> foundCar = carService.findByBarcode(savedCar.getBarcode());
        assertEquals(savedCar.getBarcode(), foundCar.getFirst().getBarcode());
    }

    @Test
    void testFindCarsByStatus() {
        Car savedCar = carService.saveCar(carObject);
        List<Car> foundCars = carService.findByStatus(CarStatus.AVAILABLE);
        assertEquals(savedCar.getBarcode(), foundCars.getFirst().getBarcode());
    }

    @Test
    void testDeleteCar(){
        Car savedCar = carService.saveCar(carObject);
        carService.deleteCar(savedCar.getBarcode());
        List<Car> foundCars = carService.findByBarcode(savedCar.getBarcode());
        assertTrue(foundCars.isEmpty());
    }

}
