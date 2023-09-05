package com.example.mobilelele_web.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrandModelsController {

    @GetMapping("/brands/all")
    public String getAllBrandAndModels() {
        return "brands";
    }
}
