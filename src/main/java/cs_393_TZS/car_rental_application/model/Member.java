package cs_393_TZS.car_rental_application.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
/*
All members can search the catalog, as well as reserve, pick-up, and return a car.
Members can have
• Name
• Address
• Email
• Phone
• Driving license number
A member can reserve more than one cars
 */

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false,unique = true)
    private String drivingLicenseNumber;

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

    public Member() {
    }

    public Member(String name, String address, String email, String phone, String drivingLicenseNumber) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
