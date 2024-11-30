package cs_393_TZS.car_rental_application.model;
import jakarta.persistence.*;

import java.util.List;

/*
The car rental system will have multiple locations, each location will have attributes
like code, ‘Name’ to distinguish it from any other locations and ‘Address’ which
defines the address of the rental location.
Each location has a code and a name. Suck as:
• 1-İstanbul Airport
• 2-İstanbul Sabiha Gökçen Airport
• 3-İstanbul Kadıköy
• 4-İzmir City Center
 */
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String address;

    @OneToMany(mappedBy = "pickUpLocation", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Reservation> pickUpReservations;

    @OneToMany(mappedBy = "dropOffLocation", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Reservation> dropOffReservations;

    public Location() {
    }

    public Location(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Reservation> getPickUpReservations() {
        return pickUpReservations;
    }

    public void setPickUpReservations(List<Reservation> pickUpReservations) {
        this.pickUpReservations = pickUpReservations;
    }

    public List<Reservation> getDropOffReservations() {
        return dropOffReservations;
    }

    public void setDropOffReservations(List<Reservation> dropOffReservations) {
        this.dropOffReservations = dropOffReservations;
    }

}

