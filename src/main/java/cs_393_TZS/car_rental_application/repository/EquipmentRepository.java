package cs_393_TZS.car_rental_application.repository;

import cs_393_TZS.car_rental_application.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment,Long> {
}
