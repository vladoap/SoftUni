package softuni.exam.models.dto;

import softuni.exam.models.enums.CarType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarSeedDto {

    @XmlElement
    private String carMake;

    @XmlElement
    private String carModel;

    @XmlElement
    private Integer year;

    @XmlElement
    private String plateNumber;

    @XmlElement
    private Integer kilometers;

    @XmlElement
    private Double engine;

    @XmlElement
    private CarType carType;

    public CarSeedDto() {
    }

    @Size(min = 2, max = 30)
    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    @Size(min = 2, max = 30)
    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    @Positive
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Size(min = 2, max = 30)
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Positive
    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }

    @Min(1)
    public Double getEngine() {
        return engine;
    }

    public void setEngine(Double engine) {
        this.engine = engine;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
