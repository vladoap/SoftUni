package com.example.productshopdatabase.model.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWIthProductsRootDto {

    @XmlAttribute(name = "count")
    private Integer count;

    @XmlElement(name = "user")
    List<UserWithAgeWithSoldProductsDto> users;

    public UserWIthProductsRootDto() {
    }

    public List<UserWithAgeWithSoldProductsDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithAgeWithSoldProductsDto> users) {
        this.users = users;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
