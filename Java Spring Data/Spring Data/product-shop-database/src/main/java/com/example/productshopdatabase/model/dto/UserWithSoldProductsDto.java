package com.example.productshopdatabase.model.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductsDto {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElement(name = "product")
    @XmlElementWrapper(name = "sold-products")
    private List<ProductSoldWithBuyerDto> soldProducts;

    public UserWithSoldProductsDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ProductSoldWithBuyerDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductSoldWithBuyerDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
