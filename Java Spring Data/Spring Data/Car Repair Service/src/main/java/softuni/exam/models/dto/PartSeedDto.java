package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


public class PartSeedDto {

    @Expose
    private String partName;

    @Expose
    private Double price;

    @Expose
    private Integer quantity;

    public PartSeedDto() {
    }

    @Size(min = 2, max = 19)
    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    @Min(10)
    @Max(2000)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Positive
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
