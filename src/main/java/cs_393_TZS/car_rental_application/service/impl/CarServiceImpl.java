package cs_393_TZS.car_rental_application.service.impl;

import cs_393_TZS.car_rental_application.model.Car;
import cs_393_TZS.car_rental_application.repository.CarRepository;
import cs_393_TZS.car_rental_application.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> searchAvailableCars(String carType, String transmissionType) {
        return carRepository.findByCarTypeAndTransmissionTypeAndStatus(carType, transmissionType, "Available");
    }
}
