package com.example.pathfinder.model.service;

import com.example.pathfinder.model.entity.Route;
import com.example.pathfinder.model.entity.User;

public class PictureServiceModel {

    private Long id;
    private String title;
    private String url;
    private User author;
    private Route route;

    public Long getId() {
        return id;
    }

    public PictureServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public PictureServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PictureServiceModel setUrl(String url) {
        this.url = url;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public PictureServiceModel setAuthor(User author) {
        this.author = author;
        return this;
    }

    public Route getRoute() {
        return route;
    }

    public PictureServiceModel setRoute(Route route) {
        this.route = route;
        return this;
    }
}
