package cs_393_TZS.car_rental_application.controller;

import cs_393_TZS.car_rental_application.DTO.LocationDTO;
import cs_393_TZS.car_rental_application.model.Location;
import org.springframework.web.bind.annotation.*;
import cs_393_TZS.car_rental_application.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    //find locations with the code
    @GetMapping("/{code}")
    public ResponseEntity<?> getLocationByCode(@PathVariable Long code) {
        LocationDTO location = locationService.findLocationByCode(code);
        if (location == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No location with the code: " + code);
        }
        return ResponseEntity.ok(location);
    }

    //list all the locations
    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        List<LocationDTO> locations = locationService.findAllLocations();
        if (locations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) // 404 Not Found
                    .body(null);
        }
        return ResponseEntity.ok(locations);
    }

    //add a new location
    @PostMapping
    public ResponseEntity<LocationDTO> addLocation(@RequestBody Location location) {
        LocationDTO locationDTO = locationService.toLocationDTO(location);
        LocationDTO newLocation = locationService.addLocation(locationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLocation); // 201 Created
    }

    //delete a location
    @DeleteMapping("/{code}")
    public ResponseEntity<?> deleteLocation(@PathVariable Long code) {
        try {
            locationService.deleteLocationByCode(code);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404 Not Found
        }
    }

    //delete all locations
    @DeleteMapping
    public ResponseEntity<?> deleteAllLocations() {
        locationService.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
    }

}
