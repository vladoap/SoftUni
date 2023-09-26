package com.example.pathfinder.model.binding;

import com.example.pathfinder.model.entity.Category;
import com.example.pathfinder.model.entity.User;
import com.example.pathfinder.model.enums.CategoryNameEnum;
import com.example.pathfinder.model.enums.LevelEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class RouteAddBindingModel {

    private Long id;
    private MultipartFile gpxCoordinates;
    private String description;
    private LevelEnum level;
    private String name;
    private String videoUrl;
    private Set<CategoryNameEnum> categories;

    public Long getId() {
        return id;
    }

    public RouteAddBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    @NotNull(message = "Upload valid coordinates")
    public MultipartFile getGpxCoordinates() {
        return gpxCoordinates;
    }

    public RouteAddBindingModel setGpxCoordinates(MultipartFile gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    @NotNull
    @Size(min = 10, message = "Description must be minimum 10 characters.")
    public String getDescription() {
        return description;
    }

    public RouteAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull(message = "Select level")
    public LevelEnum getLevel() {
        return level;
    }

    public RouteAddBindingModel setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    @NotNull
    @Size(min = 3, message = "Name must be at least 3 characters.")
    public String getName() {
        return name;
    }

    public RouteAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @Pattern(regexp = "[\\d\\D]{11}", message = "Invalid format")
    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteAddBindingModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    @NotNull
    public Set<CategoryNameEnum> getCategories() {
        return categories;
    }

    public RouteAddBindingModel setCategories(Set<CategoryNameEnum> categories) {
        this.categories = categories;
        return this;
    }
}
