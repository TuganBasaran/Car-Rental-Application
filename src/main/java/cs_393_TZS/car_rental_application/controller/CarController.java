package cs_393_TZS.car_rental_application.controller;

import cs_393_TZS.car_rental_application.DTO.CarDTO;
import cs_393_TZS.car_rental_application.DTO.ReservationDTO;
import cs_393_TZS.car_rental_application.DTO.rentedCarDTO;
import cs_393_TZS.car_rental_application.model.Car;
import cs_393_TZS.car_rental_application.model.CarStatus;
import cs_393_TZS.car_rental_application.model.CarType;
import cs_393_TZS.car_rental_application.model.Reservation;
import cs_393_TZS.car_rental_application.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cs_393_TZS.car_rental_application.service.CarService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final ReservationService reservationService;

    public CarController(CarService carService, ReservationService reservationService) {
        this.carService = carService;
        this.reservationService = reservationService;
    }

    // Add a new car
    @PostMapping
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO) {
        CarDTO newCar = carService.saveCarDTO(carDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCar);
    }

    // Get cars by status
    @GetMapping("/{status}")
    public ResponseEntity<?> getCarsByStatus(@PathVariable CarStatus status) {
        List<CarDTO> cars = carService.findCarsByStatus(status);
        if (cars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No cars found with the specified status: " + status); // 404 Not Found with message
        }
        return ResponseEntity.ok(cars); // 200 OK
    }

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableCarsByTypeAndTransmissionType(
            @RequestParam CarType carType, @RequestParam String transmissionType) {
        try {
            List<CarDTO> carDTOList = carService.findCarsByTypeAndTransmissionTypes(carType, transmissionType);
            if (carDTOList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No car found");
            }
            return ResponseEntity.ok(carDTOList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid car type or transmission type");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @GetMapping("/rented")
    public ResponseEntity<?> getAllRentedCars(){
        try {
            List<ReservationDTO> reservationDTOS = reservationService.getAllReservations();
            List<rentedCarDTO> rentedCarDTOS = new ArrayList<>();
            for(ReservationDTO reservationDTO: reservationDTOS){
                rentedCarDTO rentedCarDTO = new rentedCarDTO(reservationDTO.get)
            }

            loanedCarList.addAll(reservedCarList);
            if(loanedCarList.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no reserved or loaned cars at the moment");
            } else {
                return ResponseEntity.ok(loanedCarList);
            }
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected Error");
        }

    }

    // Delete a car
    @DeleteMapping("/{barcode}")
    public ResponseEntity<?> deleteCar(@PathVariable Long barcode) {
        try {
            boolean isDeleted = carService.deleteCar(barcode);

            if (isDeleted) {
                return ResponseEntity.ok("Car deleted successfully."); // 200 OK
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                        .body("Car is not available or is associated with a reservation and cannot be deleted."); // 406 Not Acceptable
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred: " + e.getMessage()); //500 Internal Server Error
        }
    }



}



/*
    @DeleteMapping("/{barcode}")
    public ResponseEntity<?> deleteCar(@PathVariable Long barcode) {
        try {
            carService.deleteCar(barcode);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (CarNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }


 */

//COMMENTLEDİM SİLMEDİM BAKARSIN
/*
@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    //constructor injection
    public CarController(CarService carService) {
        this.carService = carService;
    }

    // Get Cars by Status (Dynamic Endpoint with DTO)
    @GetMapping("/{status}")
    public ResponseEntity<?> getCarsByStatus(@PathVariable CarStatus status) {
        List<CarDTO> cars = carService.findCarsByStatus(status);

        if (cars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("There are no cars with the status: " + status);
        }
        return ResponseEntity.ok(cars);
    }

    // Add a New Car
    @PostMapping
    public ResponseEntity<CarDTO> addCar(@RequestBody CarDTO carDTO) {
        CarDTO newCar = carService.saveCarDTO(carDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCar);
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

    /*
    @GetMapping("/{status}")
    public ResponseEntity<?> getCarsByStatus(@PathVariable CarStatus status) {
        List<Car> cars = carService.findByStatus(status);

        if (cars.isEmpty()) {
            return ResponseEntity.status(404).body("There is no car with the status: " + status);
        }
        return ResponseEntity.ok(cars);
    }

     */

    /*
    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car newCar = carService.saveCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCar); // 201 for created
    }

     */


/*
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

 */







