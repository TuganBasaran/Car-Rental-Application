package cs_393_TZS.car_rental_application.DTO;

public class ServicesDTO {
    private Long id;
    private String name;
    private double price;

    public ServicesDTO() {
    }

    public ServicesDTO(Long id, String name, double price) {
        this.id = id;
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

    @Override
    public String toString() {
        return "ServicesDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}