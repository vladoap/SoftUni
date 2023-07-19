package softuni.exam.models.dto;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskSeedDto {

    @XmlElement(name = "date")
    private String date;

    @XmlElement(name = "price")
    private BigDecimal price;

    @XmlElement(name = "car")
    private CarWithId car;

    @XmlElement(name = "mechanic")
    private MechanicWithFirstName mechanic;

    @XmlElement(name = "part")
    private PartWithId part;

    public TaskSeedDto() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CarWithId getCar() {
        return car;
    }

    public void setCar(CarWithId car) {
        this.car = car;
    }

    public MechanicWithFirstName getMechanic() {
        return mechanic;
    }

    public void setMechanic(MechanicWithFirstName mechanic) {
        this.mechanic = mechanic;
    }

    public PartWithId getPart() {
        return part;
    }

    public void setPart(PartWithId part) {
        this.part = part;
    }
}
