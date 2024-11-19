package cs_393_TZS.car_rental_application.controller;

import cs_393_TZS.car_rental_application.model.Car;
import cs_393_TZS.car_rental_application.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/available")
    public ResponseEntity<List<Car>> searchAvailableCars(
            @RequestParam String carType,
            @RequestParam String transmissionType) {
        List<Car> availableCars = carService.searchAvailableCars(carType, transmissionType);
        if (availableCars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(availableCars, HttpStatus.OK);
    }
}
