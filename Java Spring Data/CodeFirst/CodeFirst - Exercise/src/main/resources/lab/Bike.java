package lab;

import javax.persistence.Entity;
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "bikes")
@PersistenceUnit(name = "soft_uni")
public class Bike extends Vehicle{



    public Bike(String type, String model, BigDecimal price, String fuelType) {
        super(type, model, price, fuelType);
    }

    public Bike() {

    }
}
