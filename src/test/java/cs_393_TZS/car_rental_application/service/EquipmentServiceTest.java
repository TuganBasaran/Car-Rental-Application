package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.model.Equipment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EquipmentServiceTest {

    @Autowired
    EquipmentService equipmentService;

    //Test object: Equipment
    String name = "Reserve Tire";
    double price = 12.2;

    Equipment testEquipment = new Equipment(name, price);

    @Test
    void addAndFindEquipmentById() {
        Equipment savedEquipment = equipmentService.addEquipment(testEquipment);
        Optional<Equipment> foundEquipment = equipmentService.findEquipmentById(savedEquipment.getId());
        assertEquals(savedEquipment.getId(), foundEquipment.get().getId());
    }


    @Test
    void deleteEquipmentById() {
        Equipment savedEquipment = equipmentService.addEquipment(testEquipment);
        equipmentService.deleteEquipmentById(savedEquipment.getId());
        Optional<Equipment> foundEquipment = equipmentService.findEquipmentById(savedEquipment.getId());
        assertTrue(foundEquipment.isEmpty());
    }

    @AfterEach
    void deleteAll() {
        equipmentService.deleteAll();
    }
}