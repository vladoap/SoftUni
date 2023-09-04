package com.example.mobilelele_web.model.entity;

import com.example.mobilelele_web.model.enums.Engine;
import com.example.mobilelele_web.model.enums.Transmission;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
@Data
public class Offer extends BaseEntity {

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private Engine engine;

    @Column(name = "image_url")
    private String imageUrl;

    @Column
    private Integer mileage;

    @Column(precision = 19, scale = 2)
    private BigDecimal price;

    @Column
    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @Column
    private Integer year;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime modified;

    @ManyToOne
    private Model model;

    @ManyToOne
    private User seller;
}
