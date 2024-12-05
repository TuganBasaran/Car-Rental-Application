package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.DTO.EquipmentDTO;
import cs_393_TZS.car_rental_application.model.Equipment;
import cs_393_TZS.car_rental_application.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    private EquipmentDTO toDTO(Equipment equipment) {
        return new EquipmentDTO(equipment.getId(), equipment.getName(), equipment.getPrice());
    }

    private Equipment toEntity(EquipmentDTO equipmentDTO) {
        Equipment equipment = new Equipment();
        equipment.setId(equipmentDTO.getId());
        equipment.setName(equipmentDTO.getName());
        equipment.setPrice(equipmentDTO.getPrice());
        return equipment;
    }

    public EquipmentDTO addEquipment(EquipmentDTO equipmentDTO) {
        Equipment equipment = toEntity(equipmentDTO);
        Equipment savedEquipment = equipmentRepository.save(equipment);
        return toDTO(savedEquipment);
    }

    public Optional<EquipmentDTO> findEquipmentById(Long id) {
        return equipmentRepository.findById(id).map(this::toDTO);
    }

    public List<EquipmentDTO> findAllEquipment() {
        List<Equipment> equipmentList = equipmentRepository.findAll();
        return equipmentList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteEquipmentById(Long id) {
        equipmentRepository.deleteById(id);
    }

    // Delete all equipment
    public void deleteAll() {
        equipmentRepository.deleteAll();
    }
}