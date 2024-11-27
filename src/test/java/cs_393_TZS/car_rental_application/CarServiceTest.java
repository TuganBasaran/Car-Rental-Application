package cs_393_TZS.car_rental_application;

import cs_393_TZS.car_rental_application.exception.CarNotFoundException;
import cs_393_TZS.car_rental_application.model.Car;
import cs_393_TZS.car_rental_application.model.CarStatus;
import cs_393_TZS.car_rental_application.repository.CarRepository;
import cs_393_TZS.car_rental_application.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {

    private final CarRepository carRepository = mock(CarRepository.class);
    private final CarService carService = new CarService(carRepository);

    @Test
    void testDeleteCar_WhenCarIsAvailable_ShouldDeleteCar() {
        Long barcode = 1L;
        Car car = new Car();
        car.setStatus(CarStatus.AVAILABLE);

        when(carRepository.findById(barcode)).thenReturn(Optional.of(car));

        carService.deleteCar(barcode);

        verify(carRepository, times(1)).delete(car);
    }

    @Test
    void testDeleteCar_WhenCarNotFound_ShouldThrowException() {
        Long barcode = 1L;
        when(carRepository.findById(barcode)).thenReturn(Optional.empty());

        assertThrows(CarNotFoundException.class, () -> carService.deleteCar(barcode));
    }
}
