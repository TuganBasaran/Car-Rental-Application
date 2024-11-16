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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    //GETTER VE SETTERLAR
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

    public List<Reservation>getReservations(){
        return reservations;
    }

    public void setReservations(List<Reservation> reservations){
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", drivingLicenseNumber='" + drivingLicenseNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id != null && id.equals(member.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


}
