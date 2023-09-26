package com.example.pathfinder.model.service;

import com.example.pathfinder.model.entity.Route;
import com.example.pathfinder.model.entity.User;

import java.time.LocalDateTime;

public class CommentServiceModel {

    private Long id;
    private Boolean approved;
    private LocalDateTime created;
    private String textContent;
    private User author;
    private Route route;

    public Long getId() {
        return id;
    }

    public CommentServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getApproved() {
        return approved;
    }

    public CommentServiceModel setApproved(Boolean approved) {
        this.approved = approved;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public CommentServiceModel setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentServiceModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public CommentServiceModel setAuthor(User author) {
        this.author = author;
        return this;
    }

    public Route getRoute() {
        return route;
    }

    public CommentServiceModel setRoute(Route route) {
        this.route = route;
        return this;
    }
}
