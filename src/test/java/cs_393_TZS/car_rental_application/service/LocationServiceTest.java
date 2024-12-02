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

    @SpringBootTest
    static
    class ServicesServiceTest {

        @Autowired
        ServicesService servicesService;

        ServicesDTO generateService(){
            return new ServicesDTO(null, "GPS", 12);
        }

        @Test
        void toDTO() {
            Services services = new Services("GPS", 12);
            ServicesDTO servicesDTO = servicesService.toDTO(services);
            assertEquals(services.getName(), servicesDTO.getName());
            assertEquals(services.getPrice(), servicesDTO.getPrice());
        }

        @Test
        void toEntity() {
            ServicesDTO servicesDTO = generateService();
            Services services = servicesService.toEntity(servicesDTO);
            assertEquals(services.getName(), servicesDTO.getName());
            assertEquals(services.getPrice(), servicesDTO.getPrice());
        }

        @Test
        void addServicesFindById() {
            ServicesDTO testService = generateService();
            ServicesDTO savedService = servicesService.addServices(testService);
            Optional<ServicesDTO> foundServices = servicesService.findServiceById(savedService.getId());
            assertEquals(savedService.getId(), foundServices.get().getId());
        }

        @Test
        void addServiceAndFindServicesByName() {
            ServicesDTO testService = generateService();
            ServicesDTO savedService = servicesService.addServices(testService);
            List<ServicesDTO> foundServices = servicesService.findServicesByName(savedService.getName());
            assertEquals(savedService.getName(), foundServices.get(0).getName());
            assertEquals(savedService.getId(), foundServices.get(0).getId());
        }

        @Test
        void findAll() {
            ServicesDTO services1 = new ServicesDTO(null, "Tow", 12);
            ServicesDTO services2 = new ServicesDTO(null, "Vale",21);

            servicesService.addServices(services1);
            servicesService.addServices(services2);

            List<ServicesDTO> foundServices = servicesService.findAll();
            assertEquals(2, foundServices.size());
        }

        @Test
        void deleteServiceById() {
            ServicesDTO servicesDTO = generateService();
            ServicesDTO savedService = servicesService.addServices(servicesDTO);
            System.out.println(savedService.getId());
            servicesService.deleteServiceById(savedService.getId());
            Optional<ServicesDTO> foundService =  servicesService.findServiceById(savedService.getId());
            assertTrue(foundService.isEmpty());
        }

        @AfterEach
        void deleteAll() {
            servicesService.deleteAll();
        }
    }
}