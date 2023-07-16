package com.example.productshopdatabase;

import com.example.productshopdatabase.model.dto.*;
import com.example.productshopdatabase.service.CategoryService;
import com.example.productshopdatabase.service.ProductService;
import com.example.productshopdatabase.service.UserService;
import com.example.productshopdatabase.util.XMLParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.example.productshopdatabase.constants.GlobalConstants.RESOURCES_ROOT_PATH;
import static com.example.productshopdatabase.constants.GlobalConstants.RESOURCES_ROOT_PATH_OUT;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private static final String CATEGORIES_FILE_NAME = "categories.xml";
    private static final String USERS_FILE_NAME = "users.xml";
    private static final String PRODUCTS_FILE_NAME = "products.xml";
    private static final String PRODUCTS_IN_RANGE_FILE_NAME = "products-in-range.xml";
    private static final String USERS_WITH_SOLD_PRODUCTS_FILE_NAME = "users-sold-products.xml";
    private static final String CATEGORIES_BY_PRODUCTS_FILE_NAME = "categories-by-products.xml";
    private static final String USERS_WITH_SOLD_PRODUCTS_BY_COUNT_PRODUCTS_FILE_NAME = "users-and-products.xml";
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final BufferedReader bufferedReader;
    private XMLParser xmlParser;

    public CommandLineRunnerImpl(CategoryService categoryService, UserService userService, ProductService productService, BufferedReader bufferedReader, XMLParser xmlParser) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.bufferedReader = bufferedReader;
        this.xmlParser = xmlParser;
    }

    @Override
    public void run(String... args) throws Exception {

        seedData();

        System.out.println("Enter exercise number:");
        int exNum = Integer.parseInt(bufferedReader.readLine());

        switch (exNum) {
            case 1 -> productsInRange();
            case 2 -> usersWithSoldProducts();
            case 3 -> categoriesByProductsCount();
            case 4 -> usersWithSoldProductsByCountOfProducts();
        }
    }

    private void usersWithSoldProductsByCountOfProducts() throws JAXBException, IOException {
        UserWIthProductsRootDto userWIthProductsRootDto = userService.findAllUsersWithSoldProductsOrderedByCountProducts();

        xmlParser.writeToFile(RESOURCES_ROOT_PATH_OUT + USERS_WITH_SOLD_PRODUCTS_BY_COUNT_PRODUCTS_FILE_NAME, userWIthProductsRootDto);
    }

    private void categoriesByProductsCount() throws JAXBException, IOException {
        CategoryViewRootDto categoryViewRootDto = categoryService.findAllCategoriesOrderedByProductsCount();

        xmlParser.writeToFile(RESOURCES_ROOT_PATH_OUT + CATEGORIES_BY_PRODUCTS_FILE_NAME, categoryViewRootDto);
    }

    private void usersWithSoldProducts() throws JAXBException, IOException {
        UserWIthProductsRootDto UserWIthProductsRootDto = userService.findAllUsersWithSoldProducts();

        xmlParser.writeToFile(RESOURCES_ROOT_PATH_OUT + USERS_WITH_SOLD_PRODUCTS_FILE_NAME, UserWIthProductsRootDto);

    }

    private void productsInRange() throws JAXBException, IOException {
        ProductViewRootDto productViewRootDto = productService.findAllProductsInRangeWithoutBuyer();

        xmlParser.writeToFile(RESOURCES_ROOT_PATH_OUT + PRODUCTS_IN_RANGE_FILE_NAME, productViewRootDto);
    }

    private void seedData() throws JAXBException, FileNotFoundException {
        if (categoryService.getCount() == 0) {
            CategorySeedRootDto categorySeedRootDto = xmlParser.fromFile(RESOURCES_ROOT_PATH + CATEGORIES_FILE_NAME,
                    CategorySeedRootDto.class);

            categoryService.seedCategories(categorySeedRootDto.getCategories());
        }

        if (userService.getCount() == 0) {
            UserSeedRootDto userSeedRootDto = xmlParser.fromFile(RESOURCES_ROOT_PATH + USERS_FILE_NAME,
                    UserSeedRootDto.class);

            userService.seedUsers(userSeedRootDto.getUsers());
        }

        if (productService.getCount() == 0) {
            ProductSeedRootDto productSeedRootDto = xmlParser.fromFile(RESOURCES_ROOT_PATH + PRODUCTS_FILE_NAME,
                    ProductSeedRootDto.class);

            productService.seedProducts(productSeedRootDto.getProducts());

        }
    }
}
