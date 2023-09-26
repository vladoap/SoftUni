package com.example.pathfinder.service;

import com.example.pathfinder.model.service.PictureServiceModel;

import java.util.List;

public interface PictureService {
    String findFirstPictureByRouteId(Long id);

    List<PictureServiceModel> findAllPicturesByRouteId(Long id);

    void savePictureForRoute(Long routeId, PictureServiceModel pictureServiceModel);

    List<PictureServiceModel> findAllPicturesByAuthorId(Long id);
}
