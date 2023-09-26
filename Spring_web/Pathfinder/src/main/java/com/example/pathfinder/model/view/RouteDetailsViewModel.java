package com.example.pathfinder.model.view;

import com.example.pathfinder.model.entity.Category;
import com.example.pathfinder.model.entity.User;
import com.example.pathfinder.model.enums.LevelEnum;

import java.util.Set;

public class RouteDetailsViewModel {

    private Long id;
    private String gpxCoordinates;
    private String description;
    private LevelEnum level;
    private String name;
    private User author;
    private String videoUrl;


    public Long getId() {
        return id;
    }

    public RouteDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public RouteDetailsViewModel setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public RouteDetailsViewModel setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public RouteDetailsViewModel setAuthor(User author) {
        this.author = author;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteDetailsViewModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }
}
