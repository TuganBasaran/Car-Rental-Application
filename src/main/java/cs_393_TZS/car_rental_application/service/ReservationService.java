package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.DTO.ReservationDTO;
import cs_393_TZS.car_rental_application.DTO.ReservationRequestDTO;
import cs_393_TZS.car_rental_application.model.*;
import cs_393_TZS.car_rental_application.repository.ReservationRepository;
import cs_393_TZS.car_rental_application.repository.CarRepository;
import cs_393_TZS.car_rental_application.repository.MemberRepository;
import cs_393_TZS.car_rental_application.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    //Repositories being used
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LocationRepository locationRepository;

    // Convert Entity to DTO
    public ReservationDTO toReservationDTO(Reservation reservation) {
        // totalCost hesaplama
        double totalCost = reservation.getCar().getDailyPrice() *
                ChronoUnit.DAYS.between(reservation.getPickUpDate(), reservation.getDropOffDate());

        if (reservation.getServiceList() != null) {
            totalCost += reservation.getServiceList().stream()
                    .mapToDouble(Services::getPrice)
                    .sum();
        }

        if (reservation.getEquipmentList() != null) {
            totalCost += reservation.getEquipmentList().stream()
                    .mapToDouble(Equipment::getPrice)
                    .sum();
        }

        // Equipment ve Service adlarını almak
        List<String> equipmentNames = reservation.getEquipmentList() != null
                ? reservation.getEquipmentList().stream()
                .map(Equipment::getName)
                .collect(Collectors.toList())
                : List.of();

        List<String> serviceNames = reservation.getServiceList() != null
                ? reservation.getServiceList().stream()
                .map(Services::getName)
                .collect(Collectors.toList())
                : List.of();

        // DTO oluşturma
        return new ReservationDTO(
                reservation.getReservationNumber(),
                reservation.getCreationDate(),
                reservation.getPickUpDate(),
                reservation.getDropOffDate(),
                reservation.getReturnDate(),
                reservation.getPickUpLocation().getName(),
                reservation.getDropOffLocation().getName(),
                reservation.getCar().getLicensePlate(),
                reservation.getMember().getName(),
                reservation.getStatus().toString(),
                totalCost,
                equipmentNames,
                serviceNames
        );
    }
    // DTO to Entity
    public Reservation toReservationEntity(ReservationRequestDTO request) {
        Car car = carRepository.findById(request.getCarBarcode())
                .orElseThrow(() -> new IllegalArgumentException("No car with the barcode: " + request.getCarBarcode()));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("No member with the id: " + request.getMemberId()));
        Location pickUpLocation = locationRepository.findById(request.getPickUpLocationCode())
                .orElseThrow(() -> new IllegalArgumentException("No pick up location with the code: " + request.getPickUpLocationCode()));
        Location dropOffLocation = locationRepository.findById(request.getDropOffLocationCode())
                .orElseThrow(() -> new IllegalArgumentException("No drop off location with the code: " + request.getDropOffLocationCode()));

        // Check if car is available
        if (!car.getStatus().equals(CarStatus.AVAILABLE)) {
            throw new IllegalArgumentException("Car is not available for reservation.");
        }

        // Check reservation dates
        checkReservationDates(request.getPickUpDate(), request.getDropOffDate());

        // Create a new reservation
        Reservation reservation = new Reservation();
        reservation.setReservationNumber(generateReservationNumber());
        reservation.setCreationDate(LocalDateTime.now());
        reservation.setPickUpDate(request.getPickUpDate());
        reservation.setDropOffDate(request.getDropOffDate());
        reservation.setPickUpLocation(pickUpLocation);
        reservation.setDropOffLocation(dropOffLocation);
        reservation.setCar(car);
        reservation.setMember(member);
        reservation.setStatus(ReservationStatus.PENDING);


        car.setStatus(CarStatus.RESERVED);

        return reservation;
    }


    private String generateReservationNumber() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Check reservation dates
    private void checkReservationDates(LocalDateTime pickUpDate, LocalDateTime dropOffDate) {
        if (pickUpDate.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Pick up date cannot be in the past.");
        }
        if (pickUpDate.isAfter(dropOffDate)) { //drop off:returning
            throw new IllegalArgumentException("Pick up date cannot be after drop-off date.");
        }
    }

    // Create a new reservation
    public ReservationDTO createReservation(ReservationRequestDTO request) {
        Reservation reservation = toReservationEntity(request);
        Reservation savedReservation = reservationRepository.save(reservation);
        return toReservationDTO(savedReservation);
    }

    // Find reservations by status
    public List<ReservationDTO> findReservationsByStatus(ReservationStatus status) {
        List<Reservation> reservations = reservationRepository.findByStatus(status);
        return reservations.stream()
                .map(this::toReservationDTO)
                .collect(Collectors.toList());
    }

    //loaning a car
    public void markCarAsLoaned(String reservationNumber) {
        Reservation reservation = reservationRepository.findById(reservationNumber)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with the number: " + reservationNumber));
        reservation.setStatus(ReservationStatus.ACTIVE);
        reservation.getCar().setStatus(CarStatus.LOANED);
        reservationRepository.save(reservation);
    }


    public void deleteAllReservations() {
        reservationRepository.deleteAll();
    }
}