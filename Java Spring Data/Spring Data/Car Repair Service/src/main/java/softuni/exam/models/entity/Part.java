package softuni.exam.models.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "parts")
public class Part extends BaseEntity {


    private String name;
    private Double price;
    private Integer quantity;

    public Part() {
    }

    @Column(name = "part_name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public Double getPrice() {
        return price;
    }


    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
