package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.DTO.EquipmentDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EquipmentServiceTest {

    @Autowired
    EquipmentService equipmentService;

    // Test data
    String name = "Reserve Tire";
    double price = 12.2;


    private EquipmentDTO createTestEquipmentDTO() {
        return new EquipmentDTO(null, name, price);
    }

    @Test
    void addAndFindEquipmentById() {
        EquipmentDTO testEquipment = createTestEquipmentDTO();
        EquipmentDTO savedEquipment = equipmentService.addEquipment(testEquipment);

        Optional<EquipmentDTO> foundEquipment = equipmentService.findEquipmentById(savedEquipment.getId());
        assertTrue(foundEquipment.isPresent(), "Equipment not found!");
        assertEquals(savedEquipment.getId(), foundEquipment.get().getId(), "IDs do not match!");
        assertEquals(savedEquipment.getName(), foundEquipment.get().getName(), "Names do not match!");
    }

    @Test
    void deleteEquipmentById() {
        EquipmentDTO testEquipment = createTestEquipmentDTO();
        EquipmentDTO savedEquipment = equipmentService.addEquipment(testEquipment);

        equipmentService.deleteEquipmentById(savedEquipment.getId());

        Optional<EquipmentDTO> foundEquipment = equipmentService.findEquipmentById(savedEquipment.getId());
        assertTrue(foundEquipment.isEmpty(), "Equipment was not deleted!");
    }

    @Test
    void findAllEquipment() {
        EquipmentDTO equipment1 = new EquipmentDTO(null, "Snow Tyres", 25.0);
        EquipmentDTO equipment2 = new EquipmentDTO(null, "Child Seat", 15.0);

        equipmentService.addEquipment(equipment1);
        equipmentService.addEquipment(equipment2);

        List<EquipmentDTO> allEquipment = equipmentService.findAllEquipment();
        assertEquals(2, allEquipment.size(), "Unexpected number of equipment found!");
    }

    @AfterEach
    void deleteAll() {
        equipmentService.deleteAll();
        List<EquipmentDTO> allEquipment = equipmentService.findAllEquipment();
        assertTrue(allEquipment.isEmpty(), "Database is not empty after deleteAll!");
    }
}