package com.example.mobilelele_web.model.entity;

import com.example.mobilelele_web.model.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String username;

    @Column
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "image_url")
    private String imageUrl;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime modified;

    @ManyToMany
    private Set<UserRole> roles;



}
