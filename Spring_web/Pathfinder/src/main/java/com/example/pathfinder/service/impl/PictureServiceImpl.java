package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entity.Picture;
import com.example.pathfinder.model.entity.Route;
import com.example.pathfinder.model.service.PictureServiceModel;
import com.example.pathfinder.repository.PictureRepository;
import com.example.pathfinder.service.PictureService;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.service.UserService;
import com.example.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final UserService userService;
    private final RouteService routeService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public PictureServiceImpl(PictureRepository pictureRepository, UserService userService, RouteService routeService, CurrentUser currentUser, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.userService = userService;
        this.routeService = routeService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public String findFirstPictureByRouteId(Long id) {
        String picture = pictureRepository.findFirstPictureByRouteId(id);

        return picture;
    }

    @Override
    public List<PictureServiceModel> findAllPicturesByRouteId(Long id) {
        return pictureRepository.findAllByRouteId(id).stream()
                .map(p -> modelMapper.map(p, PictureServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void savePictureForRoute(Long routeId, PictureServiceModel pictureServiceModel) {
        Route route = routeService.findRouteById(routeId);

        Picture picture = modelMapper.map(pictureServiceModel, Picture.class);

        picture
                .setRoute(route)
                .setAuthor(userService.findUserById(currentUser.getId()));

        pictureRepository.save(picture);
    }

    @Override
    public List<PictureServiceModel> findAllPicturesByAuthorId(Long id) {
        return pictureRepository.findAllByAuthorId(id).stream()
                .map(p -> modelMapper.map(p, PictureServiceModel.class))
                .collect(Collectors.toList());
    }
}
