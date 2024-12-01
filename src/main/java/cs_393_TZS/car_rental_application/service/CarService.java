package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.DTO.CarDTO;
import cs_393_TZS.car_rental_application.exception.CarNotFoundException;
import cs_393_TZS.car_rental_application.model.CarType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cs_393_TZS.car_rental_application.model.Car;
import cs_393_TZS.car_rental_application.model.CarStatus;
import cs_393_TZS.car_rental_application.repository.CarRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

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

    //Business Rule: Search Only Available Cars and rented (loaned/reserved)
    public List<Car> findByStatus(CarStatus status) {
        return carRepository.findByStatus(status);
    }

    public List<Car> findByBarcode(Long barcode) {return carRepository.findByBarcode(barcode);}

    public Car saveCar(Car car){
        return carRepository.save(car);
    }

    //Delete car
    public void deleteCar(Long barcode) {
        Car car = carRepository.findById(barcode).orElseThrow(() -> new CarNotFoundException("No car with the given barcode:  " + barcode));

        if (car.getStatus() != CarStatus.AVAILABLE) {
            throw new IllegalArgumentException("You can only delete AVAILABLE cars!" + car.getStatus());
        }
        carRepository.delete(car);
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


    public CarDTO saveCarDTO(CarDTO carDTO) {
        Car car = toCar(carDTO); // Convert DTO to entity
        Car savedCar = carRepository.save(car); // Save to the database
        return toCarDTO(savedCar); // Convert saved entity back to DTO
    }



    public List<CarDTO> findCarsByStatus(CarStatus status) {
        List<Car> cars = carRepository.findByStatus(status);
        return cars.stream()
                .map(this::toCarDTO) // DTO dönüşümü burada gerçekleşiyor
                .collect(Collectors.toList());
    }

    public List<CarDTO> findCarsByBarcode(Long barcode) {
        List<Car> cars = carRepository.findByBarcode(barcode);
        return cars.stream()
                .map(this::toCarDTO)
                .collect(Collectors.toList());
    }

    public void deleteAll(){
        carRepository.deleteAll();
    }
}
