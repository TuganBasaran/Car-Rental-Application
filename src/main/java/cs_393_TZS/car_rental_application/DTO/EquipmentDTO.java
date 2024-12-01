package cs_393_TZS.car_rental_application.DTO;

/*
DTO class for Equipment entity. It represents the data transfer object version
of the Equipment class, containing only the necessary fields for external use.
*/

public class EquipmentDTO {
    private Long id;
    private String name;
    private double price;

    public EquipmentDTO() {
    }

    public EquipmentDTO(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters and setters
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

    @Override
    public String toString() {
        return "EquipmentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}