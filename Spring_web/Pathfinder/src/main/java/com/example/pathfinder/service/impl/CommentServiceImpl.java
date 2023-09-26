package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entity.Comment;
import com.example.pathfinder.model.entity.Route;
import com.example.pathfinder.model.service.CommentServiceModel;
import com.example.pathfinder.model.service.RouteServiceModel;
import com.example.pathfinder.repository.CommentRepository;
import com.example.pathfinder.service.CommentService;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.service.UserService;
import com.example.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final RouteService routeService;
    private final ModelMapper modelMapper;


    public CommentServiceImpl(CommentRepository commentRepository, CurrentUser currentUser, UserService userService, RouteService routeService, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.currentUser = currentUser;
        this.userService = userService;
        this.routeService = routeService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addComment(Long routeId, CommentServiceModel commentServiceModel) {
        Comment comment = new Comment()
                .setCreated(LocalDateTime.now())
                .setAuthor(userService.findUserById(currentUser.getId()))
                .setRoute(routeService.findRouteById(routeId))
                .setTextContent(commentServiceModel.getTextContent())
                .setApproved(false);

        commentRepository.save(comment);
    }

    @Override
    public RouteServiceModel findTheMostCommentRoute() {
        Page<Route> routes = commentRepository.findTheMostCommentedRoute(PageRequest.of(0, 1));
        Route route = routes.getContent().get(0);
        return modelMapper.map(route, RouteServiceModel.class);
    }
}
