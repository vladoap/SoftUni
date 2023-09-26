package com.example.pathfinder.model.entity;

import com.example.pathfinder.model.enums.UserRoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity {

    private UserRoleEnum name;

    public UserRole() {
    }

    @Enumerated(EnumType.STRING)
    public UserRoleEnum getName() {
        return name;
    }

    public UserRole setName(UserRoleEnum userRoleEnum) {
        this.name = userRoleEnum;
        return this;
    }
}
