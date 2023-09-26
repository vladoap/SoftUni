package com.example.pathfinder.model.view;

import com.example.pathfinder.model.entity.Category;
import com.example.pathfinder.model.entity.User;
import com.example.pathfinder.model.enums.LevelEnum;

import java.util.Set;

public class RouteSummaryViewModel {

    private Long id;
    private String pictureUrl;
    private String description;
    private String name;

    public Long getId() {
        return id;
    }

    public RouteSummaryViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public RouteSummaryViewModel setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteSummaryViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteSummaryViewModel setName(String name) {
        this.name = name;
        return this;
    }
}
