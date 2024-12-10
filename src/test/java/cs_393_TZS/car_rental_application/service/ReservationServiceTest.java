package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.DTO.CarDTO;
import cs_393_TZS.car_rental_application.DTO.ReservationDTO;
import cs_393_TZS.car_rental_application.DTO.ReservationRequestDTO;
import cs_393_TZS.car_rental_application.model.*;
import cs_393_TZS.car_rental_application.repository.ReservationRepository;
import cs_393_TZS.car_rental_application.repository.CarRepository;
import cs_393_TZS.car_rental_application.repository.MemberRepository;
import cs_393_TZS.car_rental_application.repository.LocationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Test
    public void testCreateReservation_Success() {
        Car car = new Car();
        car.setBarcode(12345L);
        car.setStatus(CarStatus.AVAILABLE);
        car.setDailyPrice(100.0);
        car.setLicensePlate("59ZT788");
        car.setType(CarType.STANDARD);
        car.setStatus(CarStatus.AVAILABLE);
        car.setDailyPrice(12.2);
        Car savedCar = carRepository.save(car);

        Member member = new Member();
        member.setName("John Doe");
        member.setAddress("Kadıköy");
        member.setEmail("johndoe@example.com");
        member.setPhone("123456789");
        member.setDrivingLicenseNumber("A1B2C3");
        Member savedMember = memberRepository.save(member);

        Location pickUpLocation = new Location();
        pickUpLocation.setName("Istanbul Airport");
        pickUpLocation.setAddress("Istanbul");

        Location dropOffLocation = new Location();
        dropOffLocation.setName("Kadikoy");
        dropOffLocation.setAddress("Istanbul");
        Location savedPickupLocation =  locationRepository.save(pickUpLocation);
        Location savedDropoffLocation = locationRepository.save(dropOffLocation);

        ReservationRequestDTO request = new ReservationRequestDTO(
                savedCar.getBarcode(), savedMember.getId()
                , savedPickupLocation.getCode(), dropOffLocation.getCode(),
                null,null,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(3)
        );


        // Call the service method
        ReservationDTO reservationDTO = reservationService.createReservation(request);

        // Assertions
        assertNotNull(reservationDTO);
        assertEquals("John Doe", reservationDTO.getMemberName());
        assertEquals("Istanbul Airport", reservationDTO.getPickUpLocationName());
        assertEquals("Kadikoy", reservationDTO.getDropOffLocationName());
        assertEquals(24.4, reservationDTO.getTotalCost(), 0.01);


    }

    @Test
    public void testCreateReservation_CarNotAvailable() {
        Car car = new Car();
        car.setBarcode(12345L);
        car.setStatus(CarStatus.AVAILABLE);
        car.setDailyPrice(100.0);
        car.setLicensePlate("59ZT788");
        car.setType(CarType.STANDARD);
        car.setStatus(CarStatus.AVAILABLE);
        car.setDailyPrice(12.2);
        Car savedCar = carRepository.save(car);

        Member member = new Member();
        member.setName("John Doe");
        member.setAddress("Kadıköy");
        member.setEmail("johndoe@example.com");
        member.setPhone("123456789");
        member.setDrivingLicenseNumber("A1B2C3");
        Member savedMember = memberRepository.save(member);

        Location pickUpLocation = new Location();
        pickUpLocation.setName("Istanbul Airport");
        pickUpLocation.setAddress("Istanbul");

        Location dropOffLocation = new Location();
        dropOffLocation.setName("Kadikoy");
        dropOffLocation.setAddress("Istanbul");
        Location savedPickupLocation =  locationRepository.save(pickUpLocation);
        Location savedDropoffLocation = locationRepository.save(dropOffLocation);

        ReservationRequestDTO request = new ReservationRequestDTO(
                savedCar.getBarcode(), savedMember.getId()
                , savedPickupLocation.getCode(), dropOffLocation.getCode(),
                null,null,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(3)
        );

        ReservationDTO savedReservationDTO = reservationService.createReservation(request);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> reservationService.createReservation(request)
        );

        assertEquals("Car is not available for reservation.", exception.getMessage());
    }

    @Test
    public void testFindReservationsByStatus() {
        Car car = new Car();
        car.setBarcode(12345L);
        car.setStatus(CarStatus.AVAILABLE);
        car.setDailyPrice(100.0);
        car.setLicensePlate("59ZT788");
        car.setType(CarType.STANDARD);
        car.setStatus(CarStatus.AVAILABLE);
        car.setDailyPrice(12.2);
        Car savedCar = carRepository.save(car);

        Member member = new Member();
        member.setName("John Doe");
        member.setAddress("Kadıköy");
        member.setEmail("johndoe@example.com");
        member.setPhone("123456789");
        member.setDrivingLicenseNumber("A1B2C3");
        Member savedMember = memberRepository.save(member);

        Location pickUpLocation = new Location();
        pickUpLocation.setName("Istanbul Airport");
        pickUpLocation.setAddress("Istanbul");

        Location dropOffLocation = new Location();
        dropOffLocation.setName("Kadikoy");
        dropOffLocation.setAddress("Istanbul");
        Location savedPickupLocation =  locationRepository.save(pickUpLocation);
        Location savedDropoffLocation = locationRepository.save(dropOffLocation);

        ReservationRequestDTO request = new ReservationRequestDTO(
                savedCar.getBarcode(), savedMember.getId()
                , savedPickupLocation.getCode(), dropOffLocation.getCode(),
                null,null,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(3)
        );

        ReservationDTO savedReservationDTO = reservationService.createReservation(request);

        List<ReservationDTO> reservations = reservationService.findReservationsByStatus(ReservationStatus.PENDING);

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
        assertEquals(savedReservationDTO.getReservationNumber(), reservations.get(0).getReservationNumber());
        assertEquals(savedReservationDTO.getCarLicensePlate(), reservations.get(0).getCarLicensePlate());
        assertEquals(savedReservationDTO.getMemberName(), reservations.get(0).getMemberName());
        assertEquals(savedReservationDTO.getTotalCost(), reservations.get(0).getTotalCost(), 0.01);

        // Verify interactions
//        verify(reservationRepository, times(1)).findByStatus(ReservationStatus.PENDING);
    }

    @Test
    public void testMarkCarAsLoaned() {
        Car car = new Car();
        car.setBarcode(12345L);
        car.setStatus(CarStatus.AVAILABLE);
        car.setDailyPrice(100.0);
        car.setLicensePlate("59ZT788");
        car.setType(CarType.STANDARD);
        car.setStatus(CarStatus.AVAILABLE);
        car.setDailyPrice(12.2);
        Car savedCar = carRepository.save(car);

        Member member = new Member();
        member.setName("John Doe");
        member.setAddress("Kadıköy");
        member.setEmail("johndoe@example.com");
        member.setPhone("123456789");
        member.setDrivingLicenseNumber("A1B2C3");
        Member savedMember = memberRepository.save(member);

        Location pickUpLocation = new Location();
        pickUpLocation.setName("Istanbul Airport");
        pickUpLocation.setAddress("Istanbul");

        Location dropOffLocation = new Location();
        dropOffLocation.setName("Kadikoy");
        dropOffLocation.setAddress("Istanbul");
        Location savedPickupLocation =  locationRepository.save(pickUpLocation);
        Location savedDropoffLocation = locationRepository.save(dropOffLocation);

        ReservationRequestDTO request = new ReservationRequestDTO(
                savedCar.getBarcode(), savedMember.getId()
                , savedPickupLocation.getCode(), dropOffLocation.getCode(),
                null,null,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(3)
        );

        ReservationDTO savedReservationDTO = reservationService.createReservation(request);
        reservationService.markCarAsLoaned(savedReservationDTO.getReservationNumber());
        ReservationDTO foundReservationDTO = reservationService.findReservationById(savedReservationDTO.getReservationNumber());
        Car foundCar = carRepository.findCarByLicensePlate(foundReservationDTO.getCarLicensePlate());
        assertEquals(CarStatus.LOANED, foundCar.getStatus());
    }

    @BeforeEach
    public void deleteAll(){
        reservationRepository.deleteAll();
        carRepository.deleteAll();
        memberRepository.deleteAll();
        locationRepository.deleteAll();

    }
}