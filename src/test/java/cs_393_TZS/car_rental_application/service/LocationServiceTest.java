package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.DTO.LocationDTO;
import cs_393_TZS.car_rental_application.DTO.ServicesDTO;
import cs_393_TZS.car_rental_application.model.Location;
import cs_393_TZS.car_rental_application.model.Services;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationServiceTest {
    @Autowired
    LocationService locationService;

    LocationDTO generateTestObject(){
        return new LocationDTO(null, "IGA", "Istanbul");
    }

    @Test
    void toLocationDTO() {
        Location location = new Location("IGA", "Istanbul");
        LocationDTO locationDTO = locationService.toLocationDTO(location);
        assertEquals(locationDTO.getName(), location.getName());
        assertEquals(locationDTO.getAddress(), location.getAddress());
    }

    @Test
    void toEntity() {
        LocationDTO locationDTO = generateTestObject();
        Location location = locationService.toEntity(locationDTO);
        assertEquals(locationDTO.getName(), location.getName());
        assertEquals(locationDTO.getAddress(), location.getAddress());
    }

    @Test
    void addLocationAndFindLocationByCode() {
        LocationDTO locationDTO = generateTestObject();
        LocationDTO savedLocation = locationService.addLocation(locationDTO);
        LocationDTO foundLocation = locationService.findLocationByCode(savedLocation.getCode());
        assertEquals(savedLocation.getCode(), foundLocation.getCode());
        assertEquals(savedLocation.getName(), foundLocation.getName());
        assertEquals(savedLocation.getAddress(), foundLocation.getAddress());
    }

    @Test
    void addMultipleLocationsAndFindAllLocations() {
        LocationDTO locationDTO = generateTestObject();
        LocationDTO locationDTO1 = new LocationDTO(null, "SWA", "Istanbul");
        LocationDTO savedLocation = locationService.addLocation(locationDTO);
        LocationDTO savedLocation1 = locationService.addLocation(locationDTO1);
        List<LocationDTO> foundLocations = locationService.findAllLocations();
        assertEquals(2, foundLocations.size());
    }


    @Test
    void deleteLocationByCode(){
        LocationDTO locationDTO = generateTestObject();
        LocationDTO savedLocation = locationService.addLocation(locationDTO);
        LocationDTO foundLocation = locationService.findLocationByCode(savedLocation.getCode());
        assertEquals(savedLocation.getCode(), foundLocation.getCode());
        assertEquals(savedLocation.getName(), foundLocation.getName());
        assertEquals(savedLocation.getAddress(), foundLocation.getAddress());
    }

    @AfterEach
    void deleteAll() {
        locationService.deleteAll();
    }
}