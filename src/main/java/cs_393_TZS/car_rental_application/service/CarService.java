package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.DTO.CarDTO;
import cs_393_TZS.car_rental_application.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cs_393_TZS.car_rental_application.model.Car;
import cs_393_TZS.car_rental_application.model.CarStatus;
import cs_393_TZS.car_rental_application.repository.CarRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ReservationRepository reservationRepository;


    /*
    public List<Car> findByStatus(CarStatus status) {
        return carRepository.findByStatus(status);
    }
     */


    public List<CarDTO> findCarsByBarcode(Long barcode) {
        Optional<Car> cars = carRepository.findByBarcode(barcode);
        return cars.stream()
                .map(this::toCarDTO)
                .collect(Collectors.toList());
    }


    public CarDTO findCarByBarcode(Long barcode) {
        Car car = carRepository.findByBarcode(barcode)
                .orElseThrow(() -> new IllegalArgumentException("No car with the barcode: " + barcode));
        return toCarDTO(car);
    }

/*
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

 */


    public CarDTO saveCarDTO(CarDTO carDTO) {
        Car car = toCar(carDTO);
        Car savedCar = carRepository.save(car);
        return toCarDTO(savedCar);
    }


    public CarDTO toCarDTO(Car car) {
        return new CarDTO(
                car.getBrand(),
                car.getModel(),
                car.getType(),
                car.getMileage(),
                car.getTransmissionType(),
                car.getBarcode(),
                car.getLicensePlate(),
                car.getStatus()
        );
    }


    public Car toCar(CarDTO carDTO) {
        Car car = new Car();
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setType(carDTO.getType());
        car.setMileage(carDTO.getMileage());
        car.setTransmissionType(carDTO.getTransmissionType());
        car.setBarcode(carDTO.getBarcode());
        car.setLicensePlate(carDTO.getLicensePlate());
        car.setStatus(carDTO.getStatus());
        return car;
    }


    public List<CarDTO> findCarsByStatus(CarStatus status) {
        List<Car> cars = carRepository.findByStatus(status);
        return cars.stream()
                .map(this::toCarDTO)
                .collect(Collectors.toList());
    }


    public void deleteAll() {
        carRepository.deleteAll();
    }

    //delete car
    @Transactional
    public boolean deleteCar(Long barcode) {
        Car car = carRepository.findByBarcode(barcode)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with the barcode: " + barcode));

        if (car.getStatus() != CarStatus.AVAILABLE || reservationRepository.existsByCar(car)) {
            return false;
        }

        carRepository.delete(car);
        return true;
    }
}



//    public List<Car> findAvailableCars() {
//        return carRepository.findByStatus(CarStatus.AVAILABLE);
//    }
//
//    public List<Car> findReservedCars() {
//        return carRepository.findByStatus(CarStatus.RESERVED);
//    }
//
//    public List<Car> findLoanedCars() {
//        return carRepository.findByStatus(CarStatus.LOANED);
//    }
//
//    public List<Car> findLostCars() {
//        return carRepository.findByStatus(CarStatus.LOST);
//    }
//
//    public List<Car> findBeingServicedCars() {
//        return carRepository.findByStatus(CarStatus.BEING_SERVICED);
//    }