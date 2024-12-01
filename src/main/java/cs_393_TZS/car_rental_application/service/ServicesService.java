package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.DTO.EquipmentDTO;
import cs_393_TZS.car_rental_application.DTO.LocationDTO;
import cs_393_TZS.car_rental_application.DTO.ServicesDTO;
import cs_393_TZS.car_rental_application.model.Equipment;
import cs_393_TZS.car_rental_application.model.Services;
import cs_393_TZS.car_rental_application.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;

    private ServicesDTO toDTO(Services services){
        return new ServicesDTO(services.getId(), services.getName(), services.getPrice());
    }

    private Services toEntity(ServicesDTO servicesDTO){
        Services services = new Services();
        services.setId(servicesDTO.getId());
        services.setName(servicesDTO.getName());
        services.setPrice(servicesDTO.getPrice());

        return services;
    }

    public ServicesDTO addServices(ServicesDTO servicesDTO){
        Services service = toEntity(servicesDTO);
        Services savedService = servicesRepository.save(service);
        return toDTO(savedService);
    }

    public Optional<ServicesDTO> findServiceById(Long id){
        return servicesRepository.findById(id).map(this::toDTO);
    }

    public List<ServicesDTO> findServicesByName (String name){
        List<Services> foundServices = servicesRepository.findByName(name);
        return foundServices.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ServicesDTO> findAll(){
        List<Services> foundServices = servicesRepository.findAll();
        return foundServices.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteServiceById(Long id){
        servicesRepository.deleteById(id);
    }

    public void deleteAll(){
        servicesRepository.deleteAll();
    }
}
