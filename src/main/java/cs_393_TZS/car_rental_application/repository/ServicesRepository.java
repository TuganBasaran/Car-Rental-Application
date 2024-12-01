package cs_393_TZS.car_rental_application.repository;

import cs_393_TZS.car_rental_application.DTO.ServicesDTO;
import cs_393_TZS.car_rental_application.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ServicesRepository extends JpaRepository<Services, Long> {
    List<Services> findByName(String name);
}
