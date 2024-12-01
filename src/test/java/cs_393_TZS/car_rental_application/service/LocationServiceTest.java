package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.model.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationServiceTest {
    @Autowired
    LocationService locationService;

    //Test Object: Location
    String name = "Istanbul Airport";
    String address = "Istanbul";

    Location testLocation = new Location(name, address);

//    @Test
//    void addLocationAndFindLocationByCode() {
//        Location savedLocation = locationService.addLocation(testLocation);
//        Optional<Location> foundLocations = locationService.findLocationByCode(savedLocation.getCode());
//        assertEquals(savedLocation.getCode(), foundLocations.get().getCode());
//    }
//
//    @Test
//    void deleteLocationByCode() {
//        Location savedLocation = locationService.addLocation(testLocation);
//        locationService.deleteLocationByCode(savedLocation.getCode());
//        Optional<Location> foundLocations = locationService.findLocationByCode(testLocation.getCode());
//        assertTrue(foundLocations.isEmpty());
//    }

    @AfterEach
    void deleteAll() {
        locationService.deleteAll();
    }
}