package com.example.productshopdatabase.model.dto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithAgeWithSoldProductsDto {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute(name = "age")
    private Integer age;


    @XmlElement(name = "sold-products")
    private ProductSoldWrapperDto wrapperSoldProducts;


    public UserWithAgeWithSoldProductsDto() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ProductSoldWrapperDto getWrapperSoldProducts() {
        return wrapperSoldProducts;
    }

    public void setWrapperSoldProducts(ProductSoldWrapperDto wrapperSoldProducts) {
        this.wrapperSoldProducts = wrapperSoldProducts;
    }
}
