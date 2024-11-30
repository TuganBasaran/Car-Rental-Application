package cs_393_TZS.car_rental_application.repository;

import cs_393_TZS.car_rental_application.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<Services, Long> {
}
