package code_first_lab;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "cars")
public class Car extends Vehicle{

    @Column
    private Integer seats;

    @OneToOne
    private PlateNumber plateNumber;

    public Car(String type, String model, BigDecimal price, String fuelType, Integer seats) {
        super(type, model, price, fuelType);
        this.seats = seats;
    }

    public Car() {

    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
