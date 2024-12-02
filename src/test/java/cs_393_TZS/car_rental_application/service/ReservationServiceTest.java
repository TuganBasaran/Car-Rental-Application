package cs_393_TZS.car_rental_application.service;

import cs_393_TZS.car_rental_application.DTO.CarDTO;
import cs_393_TZS.car_rental_application.DTO.ReservationDTO;
import cs_393_TZS.car_rental_application.DTO.ReservationRequestDTO;
import cs_393_TZS.car_rental_application.model.*;
import cs_393_TZS.car_rental_application.repository.ReservationRepository;
import cs_393_TZS.car_rental_application.repository.CarRepository;
import cs_393_TZS.car_rental_application.repository.MemberRepository;
import cs_393_TZS.car_rental_application.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @MockBean
    private ReservationRepository reservationRepository;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private LocationRepository locationRepository;

    @Test
    public void testCreateReservation_Success() {
        // Mock entities
        Car car = new Car();
        car.setBarcode(12345L);
        car.setStatus(CarStatus.AVAILABLE);
        car.setDailyPrice(100.0);

        Member member = new Member();
        member.setId(1L);
        member.setName("John Doe");

        Location pickUpLocation = new Location();
        pickUpLocation.setName("Istanbul Airport");

        Location dropOffLocation = new Location();
        dropOffLocation.setName("Kadikoy");

        ReservationRequestDTO request = new ReservationRequestDTO(
                12345L, 1L
                , 1L, 2L,
                null,null,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(3)
        );

        when(carRepository.findById(12345L)).thenReturn(Optional.of(car));
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(locationRepository.findById(1L)).thenReturn(Optional.of(pickUpLocation));
        when(locationRepository.findById(2L)).thenReturn(Optional.of(dropOffLocation));
        when(reservationRepository.save(any(Reservation.class))).thenAnswer(i -> i.getArguments()[0]);

        // Call the service method
        ReservationDTO reservationDTO = reservationService.createReservation(request);

        // Assertions
        assertNotNull(reservationDTO);
        assertEquals("John Doe", reservationDTO.getMemberName());
        assertEquals("Istanbul Airport", reservationDTO.getPickUpLocationName());
        assertEquals("Kadikoy", reservationDTO.getDropOffLocationName());
        assertEquals(200.0, reservationDTO.getTotalCost(), 0.01);

        // Verify interactions
        verify(carRepository, times(1)).findById(12345L);
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    public void testCreateReservation_CarNotAvailable() {
        // Mock car entity
        Car car = new Car(12345L, "A1B2C3", 4,
                "Toyota", "Corolla", 12.1,
                "Manual", 20, CarType.STANDARD, CarStatus.LOANED);

        when(carRepository.findById(12345L)).thenReturn(Optional.of(car));

        // Mock member entity
        Member member = new Member();
        member.setId(1L);
        member.setName("John Doe");
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        // Mock location entities
        Location pickUpLocation = new Location("Istanbul Airport", "Istanbul");
        Location dropOffLocation = new Location("Kadikoy", "Istanbul");
        when(locationRepository.findById(1L)).thenReturn(Optional.of(pickUpLocation));
        when(locationRepository.findById(2L)).thenReturn(Optional.of(dropOffLocation));

        // Prepare request
        ReservationRequestDTO request = new ReservationRequestDTO(
                12345L, 1L, 1L, 2L,null,null,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(3)
        );

        // Expect exception
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> reservationService.createReservation(request)
        );

        // Verify exception message
        assertEquals("Car is not available for reservation.", exception.getMessage());
    }

    @Test
    public void testFindReservationsByStatus() {
        // Mock car entity
        Car car = new Car();
        car.setBarcode(12345L);
        car.setLicensePlate("34ABC123");
        car.setDailyPrice(100.0);

        // Mock member entity
        Member member = new Member();
        member.setId(1L);
        member.setName("John Doe");

        // Mock reservation entity
        Reservation reservation = new Reservation();
        reservation.setReservationNumber("RES12345");
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setCar(car); // Car entity set
        reservation.setMember(member); // Member entity set
        reservation.setPickUpLocation(new Location("Istanbul Airport", "Istanbul"));
        reservation.setDropOffLocation(new Location("Kadikoy", "Istanbul"));
        reservation.setPickUpDate(LocalDateTime.now().plusDays(1));
        reservation.setDropOffDate(LocalDateTime.now().plusDays(3));

        when(reservationRepository.findByStatus(ReservationStatus.PENDING))
                .thenReturn(List.of(reservation));

        // Call the service method
        List<ReservationDTO> reservations = reservationService.findReservationsByStatus(ReservationStatus.PENDING);

        // Assertions
        assertNotNull(reservations);
        assertEquals(1, reservations.size());
        assertEquals("RES12345", reservations.get(0).getReservationNumber());
        assertEquals("34ABC123", reservations.get(0).getCarLicensePlate());
        assertEquals("John Doe", reservations.get(0).getMemberName());
        assertEquals(200.0, reservations.get(0).getTotalCost(), 0.01);

        // Verify interactions
        verify(reservationRepository, times(1)).findByStatus(ReservationStatus.PENDING);
    }

    @Test
    public void testMarkCarAsLoaned() {
        Reservation reservation = new Reservation();
        reservation.setReservationNumber("RES12345");

        Car car = new Car();
        car.setStatus(CarStatus.RESERVED);
        reservation.setCar(car);

        when(reservationRepository.findById("RES12345")).thenReturn(Optional.of(reservation));

        // Call the service method
        reservationService.markCarAsLoaned("RES12345");

        // Assertions
        assertEquals(ReservationStatus.ACTIVE, reservation.getStatus());
        assertEquals(CarStatus.LOANED, reservation.getCar().getStatus());

        // Verify interactions
        verify(reservationRepository, times(1)).findById("RES12345");
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    public void testDeleteAllReservations() {
        reservationService.deleteAllReservations();

        // Verify interactions
        verify(reservationRepository, times(1)).deleteAll();
    }
}