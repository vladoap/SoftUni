package com.example.pathfinder.web;


import com.example.pathfinder.model.service.PictureServiceModel;
import com.example.pathfinder.model.service.RouteServiceModel;
import com.example.pathfinder.model.view.PictureViewModel;
import com.example.pathfinder.model.view.RouteSummaryViewModel;
import com.example.pathfinder.service.CommentService;
import com.example.pathfinder.service.PictureService;
import com.example.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;
    private final PictureService pictureService;
    private final CurrentUser currentUser;

    public HomeController(CommentService commentService, ModelMapper modelMapper, PictureService pictureService, CurrentUser currentUser) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
        this.pictureService = pictureService;
        this.currentUser = currentUser;
    }

    @GetMapping("/home")
    public String home(Model model) {

        RouteServiceModel routeServiceModel = commentService.findTheMostCommentRoute();
        RouteSummaryViewModel routeSummaryViewModel = modelMapper.map(routeServiceModel, RouteSummaryViewModel.class);
        routeSummaryViewModel.setPictureUrl(pictureService.findFirstPictureByRouteId(routeServiceModel.getId()));

        model.addAttribute("mostCommentedRoute", routeSummaryViewModel);

        List<PictureServiceModel> picturesServiceModel = pictureService.findAllPicturesByAuthorId(currentUser.getId());
        List<PictureViewModel> picturesViewModel = picturesServiceModel.stream()
                .map(p -> modelMapper.map(p, PictureViewModel.class))
                .toList();

        model.addAttribute("authorPictures", picturesViewModel);
        return "index";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
