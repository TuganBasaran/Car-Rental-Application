package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.exception.CarNotFoundException;
import org.springframework.stereotype.Service;
import cs_393_TZS.car_rental_application.model.Car;
import cs_393_TZS.car_rental_application.model.CarStatus;
import cs_393_TZS.car_rental_application.repository.CarRepository;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository; //final:construction injection

    //constructor injection
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAvailableCars() {
        return carRepository.findByStatus(CarStatus.AVAILABLE);
    }

    public List<Car> findReservedCars() {
        return carRepository.findByStatus(CarStatus.RESERVED);
    }

    public List<Car> findLoanedCars() {
        return carRepository.findByStatus(CarStatus.LOANED);
    }

    public List<Car> findLostCars() {
        return carRepository.findByStatus(CarStatus.LOST);
    }

    public List<Car> findBeingServicedCars() {
        return carRepository.findByStatus(CarStatus.BEING_SERVICED);
    }

    public List<Car> findCarsByStatus(CarStatus status) {
        return carRepository.findByStatus(status);
    }

    public Car saveCar(Car car){
        return carRepository.save(car);
    }

    public void deleteCar(Long barcode) {
        Car car = carRepository.findById(barcode).orElseThrow(() -> new CarNotFoundException("No car with the given barcode:  " + barcode));

        if (car.getStatus() != CarStatus.AVAILABLE) {
            throw new IllegalArgumentException("You can only delete AVAILABLE cars!" + car.getStatus());
        }

        carRepository.delete(car);
    }


}
