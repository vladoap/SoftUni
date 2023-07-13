package com.example.jsonexercise;

import com.example.jsonexercise.service.CategoryService;
import com.example.jsonexercise.service.ProductService;
import com.example.jsonexercise.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    public CommandLineRunnerImpl(UserService userService, ProductService productService, CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        productService.saveInFileAllProductsInPriceRangeAndNoBuyerOrderedByPrice();

        userService.saveInJsonAllUsersWithProductsWithAtLeastOneSoldProduct();

       categoryService.saveInJsonAllCategoriesOrderedByProductCount();

       userService.saveInJsonAllUsersWithAtleastOneSoldProduct();


    }

    private void seedData() throws IOException {
        userService.seedUsers();
        categoryService.seedCategories();
        productService.seedProducts();

    }


}
