package cs_393_TZS.car_rental_application.model;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/*
Stores details about the various types of service that members can add to their
reservation, such as :
• Additional Driver
• Towing Service
• Roadside assistance
Each service has a name and price. Price is not daily
 */
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private double price;

    @ManyToMany(mappedBy = "serviceList")
    private List<Reservation> reservations;

    public Service() {
    }

    public Service(String name, double price) {
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
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be negative value." + price);
        }
        this.price = price;
    }



}
