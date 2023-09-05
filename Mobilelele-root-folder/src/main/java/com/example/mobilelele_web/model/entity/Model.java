package com.example.mobilelele_web.model.entity;

import com.example.mobilelele_web.model.enums.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "models")
@Data
public class Model extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "start_year")
    private Integer startYear;

    @Column(name = "end_year")
    private Integer endYear;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime modified;

    @ManyToOne
    private Brand brand;
}
