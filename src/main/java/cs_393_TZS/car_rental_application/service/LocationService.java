package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.DTO.LocationDTO;
import cs_393_TZS.car_rental_application.model.Location;
import cs_393_TZS.car_rental_application.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    public LocationDTO toLocationDTO(Location location) {
        return new LocationDTO(location.getCode(), location.getName(), location.getAddress());
    }


    public Location toEntity(LocationDTO locationDTO){
        return new Location(locationDTO.getName(), locationDTO.getAddress());
    }

    public LocationDTO findLocationByCode(Long code) {
        Location location = locationRepository.findById(code)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find the location of the code:  " + code));
        return toLocationDTO(location);
    }

    public List<LocationDTO> findAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream()
                .map(this::toLocationDTO)
                .collect(Collectors.toList());
    }


    public LocationDTO addLocation(LocationDTO locationDTO) {
        Location savedLocation = locationRepository.save(toEntity(locationDTO));
        return toLocationDTO(savedLocation);
    }


    public void deleteLocationByCode(Long code) {
        if (!locationRepository.existsById(code)) {
            throw new IllegalArgumentException("There are no locations with the code: " + code);
        }
        locationRepository.deleteById(code);
    }

    public void deleteAll() {
        locationRepository.deleteAll();
    }
}


