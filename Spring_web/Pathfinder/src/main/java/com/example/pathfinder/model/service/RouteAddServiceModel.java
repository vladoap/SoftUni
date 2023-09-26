package com.example.pathfinder.model.service;

import com.example.pathfinder.model.enums.CategoryNameEnum;
import com.example.pathfinder.model.enums.LevelEnum;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class RouteAddServiceModel {

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

    public RouteAddServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public MultipartFile getGpxCoordinates() {
        return gpxCoordinates;
    }

    public RouteAddServiceModel setGpxCoordinates(MultipartFile gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public RouteAddServiceModel setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteAddServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteAddServiceModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public Set<CategoryNameEnum> getCategories() {
        return categories;
    }

    public RouteAddServiceModel setCategories(Set<CategoryNameEnum> categories) {
        this.categories = categories;
        return this;
    }
}
