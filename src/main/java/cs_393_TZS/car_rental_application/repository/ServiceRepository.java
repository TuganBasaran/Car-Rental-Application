package cs_393_TZS.car_rental_application.repository;

import cs_393_TZS.car_rental_application.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
