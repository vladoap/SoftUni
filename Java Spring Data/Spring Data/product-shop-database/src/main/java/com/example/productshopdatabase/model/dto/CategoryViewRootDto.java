package com.example.productshopdatabase.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryViewRootDto {

    @XmlElement(name = "category")
    List<CategoryByProductCountDto> categories;

    public CategoryViewRootDto() {
    }

    public List<CategoryByProductCountDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryByProductCountDto> categories) {
        this.categories = categories;
    }
}
