package com.example.game_store.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameAddDto {

    private String title;
    private String trailer;
    private String imageThumbnailUrl;
    private Double size;
    private BigDecimal price;
    private String description;
    private String releaseDate;

    public GameAddDto() {
    }

    public GameAddDto(String title, String trailer, String imageThumbnail,
                      Double size, BigDecimal price, String description,
                      String releaseDate) {
        this.title = title;
        this.trailer = trailer;
        this.imageThumbnailUrl = imageThumbnail;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    @Pattern(regexp = "[A-Z][A-Za-z\\d]{2,99}", message = "Invalid title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Pattern(regexp = "[A-Za-z\\d]{11}", message = "Invalid trailer!")
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Pattern(regexp = "(https?).*", message = "Invalid image thumbnail!")
    public String getImageThumbnail() {
        return imageThumbnailUrl;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnailUrl = imageThumbnail;
    }

    @Positive(message = "Negative size!")
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Positive(message = "Negative price!")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Size(min = 20)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
