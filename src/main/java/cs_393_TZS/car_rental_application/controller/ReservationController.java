package cs_393_TZS.car_rental_application.controller;

import cs_393_TZS.car_rental_application.DTO.MemberDTO;
import cs_393_TZS.car_rental_application.DTO.ReservationDTO;
import cs_393_TZS.car_rental_application.DTO.ReservationRequestDTO;
import cs_393_TZS.car_rental_application.model.ReservationStatus;
import cs_393_TZS.car_rental_application.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequestDTO reservationRequestDTO) {
        try {
            ReservationDTO createdReservation = reservationService.createReservation(reservationRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation); // 201 Created
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE) // 406 Not Acceptable
                    .body(e.getMessage());
        } /*catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT) // 206 Partial Content
                    .body(e.getMessage());
        }
        */
    }

    // Get reservations by status
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getReservationsByStatus(@PathVariable ReservationStatus status) {
        List<ReservationDTO> reservations = reservationService.findReservationsByStatus(status);
        if (reservations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No reservations found with the given status: " + status); // 404 Not Found
        }
        return ResponseEntity.ok(reservations); // 200 OK
    }
    @PutMapping("/{reservationNumber}/services")
    public ResponseEntity<Boolean> addServiceToReservation(
            @PathVariable String reservationNumber,
            @RequestParam Long serviceCode) {
        try {
            boolean isAdded = reservationService.addAdditionalService(reservationNumber, serviceCode);
            if (isAdded) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
    @PutMapping("/{reservationNumber}/equipments")
    public ResponseEntity<Boolean> addEquipmentToReservation(
            @PathVariable String reservationNumber,
            @RequestParam Long equipmentCode) {
        try {
            boolean isAdded = reservationService.addAdditionalEquipment(reservationNumber, equipmentCode);
            if (isAdded) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }



    // Loaning a car
    @PatchMapping("/{reservationNumber}/loaned")
    public ResponseEntity<?> markCarAsLoaned(@PathVariable String reservationNumber) {
        try {
            reservationService.markCarAsLoaned(reservationNumber);
            return ResponseEntity.ok("Reservation marked as ACTIVE, and car status updated to LOANED."); // 200 OK
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); // 400 Bad Request
        }
    }

    // Delete all reservations
    @DeleteMapping
    public ResponseEntity<?> deleteAllReservations() {
        reservationService.deleteAllReservations();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
    }
    // Delete all reservations
    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable String id) {
        try {
            ReservationDTO reservation = reservationService.findReservationById(id);
            return ResponseEntity.ok(reservation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // 404 Not Found
        }
    }


    //return car
    @PutMapping("/{reservationNumber}/returned")
    public ResponseEntity<?> markCarAsReturned(@PathVariable String reservationNumber,
                                               @RequestParam(required = false) String returnDate) {
        try {
            boolean isReturned = reservationService.markCarAsReturned(
                    reservationNumber,
                    returnDate != null ? LocalDateTime.parse(returnDate) : null
            );
            if (isReturned) {
                return ResponseEntity.ok("Reservation marked as RETURNED, and car status updated to AVAILABLE."); // 200 OK
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation not found or already completed/cancelled."); // 404 Not Found
            }
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Invalid date format. Please use the format: yyyy-MM-ddTHH:mm:ss"); // 500 Internal Server Error
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred: " + e.getMessage()); // 500 Internal Server Error
        }
    }




    //cancel reservation
    @PutMapping("/{reservationNumber}/cancelled")
    public ResponseEntity<?> markCarAsCancelled(@PathVariable String reservationNumber) {
        try {
            boolean isCancelled = reservationService.markCarAsCancelled(reservationNumber);
            if (isCancelled) {
                return ResponseEntity.ok("Reservation marked as CANCELLED, and car status updated to AVAILABLE."); // 200 OK
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation not found or already completed/cancelled."); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred: " + e.getMessage()); // 500 Internal Server Error
        }
    }



    //delete reservation
    @DeleteMapping("/{reservationNumber}")
    public ResponseEntity<?> deleteReservation(@PathVariable String reservationNumber) {
        try {
            boolean isDeleted = reservationService.deleteReservation(reservationNumber);
            if (isDeleted) {
                return ResponseEntity.ok("Reservation deleted successfully."); // 200 OK
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation not found."); // 404 Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred: " + e.getMessage()); // 500 Internal Server Error
        }
    }




}



