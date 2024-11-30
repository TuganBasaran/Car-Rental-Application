package cs_393_TZS.car_rental_application.controller;

import cs_393_TZS.car_rental_application.exception.CarNotFoundException;
import cs_393_TZS.car_rental_application.model.CarStatus;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import cs_393_TZS.car_rental_application.model.Car;
import cs_393_TZS.car_rental_application.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    //constructor injection
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/available")
    public ResponseEntity<List<Car>> getAvailableCars() {
        return ResponseEntity.ok(carService.findByStatus(CarStatus.AVAILABLE));
    }

    @GetMapping("/reserved")
    public ResponseEntity<List<Car>> getReservedCars() {
        return ResponseEntity.ok(carService.findByStatus(CarStatus.AVAILABLE));
    }

    @GetMapping("/being-serviced")
    public ResponseEntity<List<Car>> getBeingServicedCars() {
        return ResponseEntity.ok(carService.findByStatus(CarStatus.BEING_SERVICED));
    }

    @GetMapping("/loaned")
    public ResponseEntity<List<Car>> getLoanedCars() {
        return ResponseEntity.ok(carService.findByStatus(CarStatus.LOANED));
    }

    @GetMapping("/lost")
    public ResponseEntity<List<Car>> getLostCars() {
        return ResponseEntity.ok(carService.findByStatus(CarStatus.LOST));
    }

    @GetMapping("/{status}")
    public ResponseEntity<?> getCarsByStatus(@PathVariable CarStatus status) {
        List<Car> cars = carService.findByStatus(status);

        if (cars.isEmpty()) {
            return ResponseEntity.status(404).body("There is no car with the status: " + status);
        }
        return ResponseEntity.ok(cars);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car newCar = carService.saveCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCar); // 201 for created
    }

    @DeleteMapping("/{barcode}") //id ile araç silme ama id yi barcode tanımlamıştık o yüzden böyle bıraktım
    public ResponseEntity<?> deleteCar(@PathVariable Long barcode) {
        try {
            carService.deleteCar(barcode); // Araç silinir
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        } catch (CarNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404 Not Found
        }
    }



}









