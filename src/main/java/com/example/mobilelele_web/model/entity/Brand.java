package com.example.mobilelele_web.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "brands")
@Data
public class Brand extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime modified;


}
