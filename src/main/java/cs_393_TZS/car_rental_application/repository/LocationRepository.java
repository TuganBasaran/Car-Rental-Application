package cs_393_TZS.car_rental_application.repository;

import cs_393_TZS.car_rental_application.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
