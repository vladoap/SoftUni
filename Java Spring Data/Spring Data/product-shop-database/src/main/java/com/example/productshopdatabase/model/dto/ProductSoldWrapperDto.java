package com.example.productshopdatabase.model.dto;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSoldWrapperDto {

    @XmlAttribute(name = "count")
    private Integer count;

    @XmlElement(name = "product")
    private List<ProductSoldDto> products;

    public ProductSoldWrapperDto() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductSoldDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSoldDto> products) {
        this.products = products;
    }
}
