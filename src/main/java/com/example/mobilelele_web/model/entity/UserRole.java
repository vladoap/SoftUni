package com.example.mobilelele_web.model.entity;

import com.example.mobilelele_web.model.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class UserRole extends BaseEntity{

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
}
