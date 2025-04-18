package cs_393_TZS.car_rental_application.model;
import java.util.List;

import jakarta.persistence.*;
/*
Stores details about the various types of equipment that members can add to their
reservation. Some of them are:
• Snow Tyres
• Child Seat
• Baby Seat
• Roof Box
• WIFI
• GPS
Each equipment has a name and price. Price is not daily
 */

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private double price;

    @ManyToMany(mappedBy = "equipmentList")
    private List<Reservation> reservations;

    public Equipment() {
    }

    public Equipment(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
