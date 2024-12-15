package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.DTO.ReservationDTO;
import cs_393_TZS.car_rental_application.DTO.ReservationRequestDTO;
import cs_393_TZS.car_rental_application.model.*;
import cs_393_TZS.car_rental_application.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
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
    @Autowired
    private ServicesRepository servicesRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;

    // Convert Entity to DTO
    @Transactional
    public ReservationDTO toReservationDTO(Reservation reservation) {
        // Equipment ve Service adlarını almak
        List<String> equipmentNames = reservation.getEquipmentList() != null
                ? reservation.getEquipmentList().stream()
                .map(Equipment::getName)
                .collect(Collectors.toList())
                : List.of();

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
        List<Services> servicesList = servicesRepository.findAllById(request.getAdditionalServiceIds());
        List<Equipment> equipmentList = equipmentRepository.findAllById(request.getAdditionalEquipmentIds());

        // Check if car is available
        if (!car.getStatus().equals(CarStatus.AVAILABLE)) {
            throw new IllegalArgumentException("Car is not available for reservation.");
        }

        // Calculate drop-off date
        LocalDateTime dropOffDate = request.getPickUpDate().plusDays(request.getDayCount());

        // Create a new reservation
        Reservation reservation = new Reservation();
        reservation.setReservationNumber(generateReservationNumber());
        reservation.setCreationDate(LocalDateTime.now());
        reservation.setPickUpDate(reservation.getCreationDate().plusDays(1));
        reservation.setDropOffDate(reservation.getPickUpDate().plusDays(request.getDayCount()));
        reservation.setPickUpLocation(pickUpLocation);
        reservation.setDropOffLocation(dropOffLocation);
        reservation.setCar(car);
        reservation.setMember(member);
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setServiceList(servicesList);
        reservation.setEquipmentList(equipmentList);


        car.setStatus(CarStatus.LOANED);

        return reservation;
    }


    private String generateReservationNumber() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }


    // Create a new reservation
    @Transactional
    public ReservationDTO createReservation(ReservationRequestDTO request) {
        Reservation reservation = toReservationEntity(request);
        Reservation savedReservation = reservationRepository.save(reservation);
        return toReservationDTO(savedReservation);
    }

    // Find reservations by status
    @Transactional
    public List<ReservationDTO> findReservationsByStatus(ReservationStatus status) {
        List<Reservation> reservations = reservationRepository.findByStatus(status);
        return reservations.stream()
                .map(this::toReservationDTO)
                .collect(Collectors.toList());
    }

    //loaning a car
    @Transactional
    public void markCarAsLoaned(String reservationNumber) {
        Reservation reservation = reservationRepository.findById(reservationNumber)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with the number: " + reservationNumber));
        reservation.setStatus(ReservationStatus.ACTIVE);
        reservation.getCar().setStatus(CarStatus.LOANED);
        reservationRepository.save(reservation);
    }
    public boolean addAdditionalService(String reservationNumber, Long serviceCode) {
        // Fetch the reservation by reservationNumber
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationNumber);

        if (reservationOptional.isEmpty()) {
            return false; // Reservation not found
        }

        Reservation reservation = reservationOptional.get();

        // Fetch the service by serviceCode
        Optional<Services> serviceOptional = servicesRepository.findById(serviceCode);

        if (serviceOptional.isEmpty()) {
            return false; // Service not found
        }

        Services service = serviceOptional.get();

        // Check if the service is already added to the reservation
        if (reservation.getServiceList().contains(service)) {
            return false; // Service already added
        }

        // Add the service to the reservation
        reservation.getServiceList().add(service);
        reservationRepository.save(reservation);

        return true; // Service added successfully
    }
    public boolean addAdditionalEquipment(String reservationNumber, Long equipmentCode) {

        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationNumber);

        if (reservationOptional.isEmpty()) {
            return false;
        }

        Reservation reservation = reservationOptional.get();

        Optional<Equipment> equipmentOptional = equipmentRepository.findById(equipmentCode);

        if (equipmentOptional.isEmpty()) {
            return false;
        }

        Equipment equipment = equipmentOptional.get();


        if (reservation.getEquipmentList().contains(equipment)) {
            return false;
        }


        reservation.getEquipmentList().add(equipment);
        reservationRepository.save(reservation);

        return true;
    }



    public void deleteAllReservations() {
        reservationRepository.deleteAll();
    }


    @Transactional
    public ReservationDTO findReservationById(String reservationId){
        Optional<Reservation> foundReservation = reservationRepository.findById(reservationId);
        return toReservationDTO(foundReservation.get());
    }


    //return car
    @Transactional
    public boolean markCarAsReturned(String reservationNumber) {

        Reservation reservation = reservationRepository.findById(reservationNumber)
                .orElse(null);

        if (reservation == null || reservation.getStatus() == ReservationStatus.COMPLETED ||
                reservation.getStatus() == ReservationStatus.CANCELLED) {
            return false;
        }

        reservation.setReturnDate(LocalDateTime.now());

        reservation.setStatus(ReservationStatus.COMPLETED);
        reservation.getCar().setStatus(CarStatus.AVAILABLE);
        reservationRepository.save(reservation);
        return true;
    }


    //cancel reservation
    @Transactional
    public boolean markCarAsCancelled(String reservationNumber) {
        Reservation reservation = reservationRepository.findById(reservationNumber).orElse(null);

        if (reservation == null || reservation.getStatus() == ReservationStatus.CANCELLED ||
                reservation.getStatus() == ReservationStatus.COMPLETED) {
            return false;
        }

        reservation.setStatus(ReservationStatus.CANCELLED);
        reservation.getCar().setStatus(CarStatus.AVAILABLE);
        reservationRepository.save(reservation);
        return true;
    }


    //delete reservation
    @Transactional
    public boolean deleteReservation(String reservationNumber) {
        try {
            Reservation reservation = reservationRepository.findById(reservationNumber)
                    .orElseThrow(() -> new IllegalArgumentException("Reservation not found with number: " + reservationNumber));

            reservation.setCar(null);
            reservation.setMember(null);

            reservationRepository.delete(reservation);

            return true;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting reservation: " + e.getMessage(), e);
        }
    }

    @Transactional
    public boolean returnCar(String reservationNumber){
        try {
            Reservation reservation = reservationRepository.findById(reservationNumber)
                    .orElseThrow(() -> new IllegalArgumentException("Reservation not found with number: " + reservationNumber));

            //Updating car status
            Car car = reservation.getCar();
            car.setStatus(CarStatus.AVAILABLE);

            //Updating Reservation Status and Return Date
            reservation.setStatus(ReservationStatus.COMPLETED);
            reservation.setReturnDate(LocalDateTime.now());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (Exception e){
            return false;
        }
    }



}