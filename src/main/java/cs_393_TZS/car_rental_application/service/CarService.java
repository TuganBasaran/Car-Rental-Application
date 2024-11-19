package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.model.Car;

import java.util.List;

public interface CarService {
    List<Car> searchAvailableCars(String carType, String transmissionType);
}
