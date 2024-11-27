package cs_393_TZS.car_rental_application;

import cs_393_TZS.car_rental_application.controller.CarController;
import cs_393_TZS.car_rental_application.exception.CarNotFoundException;
import cs_393_TZS.car_rental_application.model.Car;
import cs_393_TZS.car_rental_application.model.CarStatus;
import cs_393_TZS.car_rental_application.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    void testGetAvailableCars_ShouldReturnCarList() throws Exception {
        // Arrange
        Car car = new Car();
        car.setStatus(CarStatus.AVAILABLE);

        when(carService.findAvailableCars()).thenReturn(Collections.singletonList(car));

        // Act & Assert
        mockMvc.perform(get("/cars/available"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("AVAILABLE"));
    }

    @Test
    void testDeleteCar_ShouldReturnNoContent() throws Exception {
        // Arrange
        doNothing().when(carService).deleteCar(1L);

        // Act & Assert
        mockMvc.perform(delete("/cars/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteCar_ShouldReturnNotFound() throws Exception {
        // Arrange
        doThrow(new CarNotFoundException("Car not found with barcode: 1")).when(carService).deleteCar(1L);

        // Act & Assert
        mockMvc.perform(delete("/cars/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Car not found with barcode: 1"));
    }

}
