package com.example.pathfinder.web;

import com.example.pathfinder.model.binding.CommentBindingModel;
import com.example.pathfinder.model.binding.RouteAddBindingModel;
import com.example.pathfinder.model.service.CommentServiceModel;
import com.example.pathfinder.model.service.PictureServiceModel;
import com.example.pathfinder.model.service.RouteAddServiceModel;
import com.example.pathfinder.model.view.PictureViewModel;
import com.example.pathfinder.model.view.RouteDetailsViewModel;
import com.example.pathfinder.model.view.RouteSummaryViewModel;
import com.example.pathfinder.service.CommentService;
import com.example.pathfinder.service.PictureService;
import com.example.pathfinder.service.RouteService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final ModelMapper modelMapper;
    private final PictureService pictureService;
    private final CommentService commentService;

    public RouteController(RouteService routeService, ModelMapper modelMapper, PictureService pictureService, CommentService commentService) {
        this.routeService = routeService;
        this.modelMapper = modelMapper;
        this.pictureService = pictureService;
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public String all(Model model) {

        List<RouteSummaryViewModel> routeViewModel = routeService.getAllRoutes().stream()
                .map(r -> {
                    RouteSummaryViewModel routeView = modelMapper.map(r, RouteSummaryViewModel.class);
                    routeView.setPictureUrl(pictureService.findFirstPictureByRouteId(r.getId()));

                    return routeView;
                }).collect(Collectors.toList());

        model.addAttribute("routes", routeViewModel);



        return "routes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {

        model.addAttribute("route", modelMapper.map(routeService.findById(id), RouteDetailsViewModel.class));

        List<PictureViewModel> pictureViewModel = pictureService.findAllPicturesByRouteId(id).stream().map(p -> modelMapper.map(p, PictureViewModel.class)).collect(Collectors.toList());
        model.addAttribute("pictures", pictureViewModel);
        return "route-details";
    }

    @PostMapping("/details/{id}/comment")
    public String addComment(@PathVariable Long id, @Valid CommentBindingModel commentBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
         if (bindingResult.hasErrors()) {
             redirectAttributes.addFlashAttribute("commentBindingModel", commentBindingModel);
             redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.commentBindingModel", bindingResult);
         }

         commentService.addComment(id, modelMapper.map(commentBindingModel, CommentServiceModel.class));

        return "redirect:/routes/details/" + id;
    }

    @PostMapping("/details/{id}/picture")
    public String addPicture(@PathVariable Long id, @RequestParam("picture") MultipartFile file) {

        if (file.isEmpty()) {
            // Handle the case where no file was uploaded
            return "redirect:/routes/details/" + id;
        }

        try {
            // Define the directory where you want to save the uploaded file
            String uploadDir = "C:/Users/z00431ym/Downloads"; // Replace with your desired directory


            // Generate a unique file name
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uniqueFileName = System.currentTimeMillis() + "_" + fileName;

            // Create the absolute path to the saved file
            Path absolutePath = Paths.get(uploadDir + File.separator + uniqueFileName);

            // Save the file to the specified directory
            file.transferTo(absolutePath.toFile());

            // Now you have the absolute path to the saved file
            String absoluteFilePath = absolutePath.toString();

            // Do whatever you need with the absoluteFilePath, e.g., store it in a database
            PictureServiceModel pictureServiceModel = new PictureServiceModel();
            pictureServiceModel.setUrl(absoluteFilePath);

            pictureService.savePictureForRoute(id, pictureServiceModel);

            return "redirect:/routes/details/" + id;
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
        }


        return "redirect:/routes/details/" + id;
    }

    @GetMapping("/add")
    public String addRoute(Model model) {


        return "add-route";
    }

    @PostMapping("add")
    public String addRoutePost(@Valid RouteAddBindingModel routeAddBindingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("routeAddBindingModel", routeAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.routeAddBindingModel", bindingResult);
            return "redirect:add";
        }

        RouteAddServiceModel routeServiceModel = modelMapper.map(routeAddBindingModel, RouteAddServiceModel.class);

        routeService.addRoute(routeServiceModel);


        return "redirect:/";
    }

    @GetMapping("{categoryName}")
    public String getRouteByCategoryName(@PathVariable String categoryName, Model model) {

        List<RouteSummaryViewModel> routeSummaryViewModel = routeService.findRoutesByCategoryName(categoryName)
                .stream()
                .map(routeServiceModel ->  {
                    RouteSummaryViewModel viewModel = modelMapper.map(routeServiceModel, RouteSummaryViewModel.class);
                    viewModel.setPictureUrl(pictureService.findFirstPictureByRouteId(routeServiceModel.getId()));

                    return viewModel;
                })
                .toList();

        model.addAttribute("routesForCategory", routeSummaryViewModel);

        String returnPage = null;
        switch (categoryName) {
            case "pedestrian" -> returnPage = "pedestrian";
            case "bicycle" -> returnPage = "bicycle";
            case "motorcycle" -> returnPage = "motorcycle";
            case "car" -> returnPage = "car";
        }


        return returnPage;
    }

    @ModelAttribute
    public CommentBindingModel commentBindingModel() {
        return new CommentBindingModel();
    }

    @ModelAttribute
    public RouteAddBindingModel routeAddBindingModel() {
        return new RouteAddBindingModel();
    }


}
