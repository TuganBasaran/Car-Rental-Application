package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.model.Equipment;
import cs_393_TZS.car_rental_application.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    EquipmentRepository equipmentRepository;

    public Equipment addEquipment(Equipment equipment){
        return equipmentRepository.save(equipment);
    }

    public Optional<Equipment> findEquipmentById(Long id){
        return equipmentRepository.findById(id);
    }

    public void deleteEquipmentById(Long id){
        equipmentRepository.deleteById(id);
    }

    public void deleteAll(){
        equipmentRepository.deleteAll();
    }
}
