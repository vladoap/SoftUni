package com.example.pathfinder.service;

import com.example.pathfinder.model.entity.Route;
import com.example.pathfinder.model.service.CommentServiceModel;
import com.example.pathfinder.model.service.RouteServiceModel;

public interface CommentService {
    void addComment(Long routeId, CommentServiceModel commentServiceModel);

    RouteServiceModel findTheMostCommentRoute();
}
