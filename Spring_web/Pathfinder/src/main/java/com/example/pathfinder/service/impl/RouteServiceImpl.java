package com.example.pathfinder.service.impl;

import com.example.pathfinder.model.entity.Route;
import com.example.pathfinder.model.enums.CategoryNameEnum;
import com.example.pathfinder.model.service.RouteAddServiceModel;
import com.example.pathfinder.model.service.RouteServiceModel;
import com.example.pathfinder.repository.RouteRepository;
import com.example.pathfinder.service.CategoryService;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.service.UserService;
import com.example.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public RouteServiceImpl(RouteRepository routeRepository, CurrentUser currentUser, UserService userService, CategoryService categoryService, ModelMapper modelMapper) {
        this.routeRepository = routeRepository;
        this.currentUser = currentUser;
        this.userService = userService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RouteServiceModel> getAllRoutes() {
       return routeRepository.findAll()
                .stream()
                .map(route -> {
                    RouteServiceModel routeServiceModel = modelMapper.map(route, RouteServiceModel.class);
                    routeServiceModel.setId(route.getId());

                    return routeServiceModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public RouteServiceModel findById(Long id) {
        return modelMapper.map(routeRepository.findById(id), RouteServiceModel.class);
    }

    @Override
    public Route findRouteById(Long routeId) {
        return routeRepository.findById(routeId).orElse(null);
    }

    @Override
    public void addRoute(RouteAddServiceModel routeServiceModel) throws IOException {
        Route route = modelMapper.map(routeServiceModel, Route.class);
        route.setAuthor(userService.findUserById(currentUser.getId()));
        route.setGpxCoordinates(new String(routeServiceModel.getGpxCoordinates().getBytes()));
        route.setCategories(routeServiceModel.getCategories()
                .stream()
                .map(categoryService::findByCategoryName)
                        .collect(Collectors.toSet()));

        routeRepository.save(route);
    }

    @Override
    public List<RouteServiceModel> findRoutesByCategoryName(String categoryName) {
        List<Route> routes = routeRepository.findAllByCategoryName(CategoryNameEnum.valueOf(categoryName.toUpperCase()));

        return routes.stream().map(route -> modelMapper.map(route, RouteServiceModel.class)).collect(Collectors.toList());
    }
}
