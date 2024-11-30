package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.model.Location;
import cs_393_TZS.car_rental_application.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    Location addLocation(Location location){
        return locationRepository.save(location);
    }

    Optional<Location> findLocationByCode(Long code){
        return locationRepository.findById(code);
    }

    void deleteLocationByCode(Long code){
        locationRepository.deleteById(code);
    }

    void deleteAll(){
        locationRepository.deleteAll();
    }
}
