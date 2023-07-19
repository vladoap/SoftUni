package softuni.exam.models.entity;

import softuni.exam.models.enums.ApartmentType;

import javax.persistence.*;

@Entity
@Table(name = "apartments")
public class Apartment extends BaseEntity{


    private ApartmentType type;
    private Double area;
    private Town town;

    public Apartment() {
    }

    @Column(name = "apartment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    public ApartmentType getType() {
        return type;
    }

    public void setType(ApartmentType type) {
        this.type = type;
    }

    @Column(nullable = false)
    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    @ManyToOne
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
