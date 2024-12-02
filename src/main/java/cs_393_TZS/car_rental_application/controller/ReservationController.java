package cs_393_TZS.car_rental_application.controller;

import cs_393_TZS.car_rental_application.DTO.ReservationDTO;
import cs_393_TZS.car_rental_application.DTO.ReservationRequestDTO;
import cs_393_TZS.car_rental_application.model.ReservationStatus;
import cs_393_TZS.car_rental_application.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
