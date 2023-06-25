package lab;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "planes")
@PersistenceUnit(name = "soft_uni")
public class Plane extends Vehicle{

    @Column(name = "passenger_capacity")
    private Integer passengerCapacity;

    @ManyToOne
    private Company company;

    public Plane(String type, String model, BigDecimal price, String fuelType, Integer passengerCapacity) {
        super(type, model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
    }

    public Plane() {
    }

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
}
