package com.example.pathfinder.service;

import com.example.pathfinder.model.entity.Route;
import com.example.pathfinder.model.service.RouteAddServiceModel;
import com.example.pathfinder.model.service.RouteServiceModel;

import java.io.IOException;
import java.util.List;

public interface RouteService {

    List<RouteServiceModel> getAllRoutes();

    RouteServiceModel findById(Long id);

    Route findRouteById(Long routeId);

    void addRoute(RouteAddServiceModel routeServiceModel) throws IOException;

    List<RouteServiceModel> findRoutesByCategoryName(String categoryName);
}
