package cs_393_TZS.car_rental_application.exception;

public class InvalidCarStatusException extends RuntimeException {
    public InvalidCarStatusException(String message) {
        super(message);
    }
}
