package com.example.jsonexercise.service.impl;

import com.example.jsonexercise.model.Category;
import com.example.jsonexercise.model.Product;
import com.example.jsonexercise.model.dto.ProductSeedDto;
import com.example.jsonexercise.model.dto.ProductsInRangeNoBuyer;
import com.example.jsonexercise.repository.ProductRepository;
import com.example.jsonexercise.service.CategoryService;
import com.example.jsonexercise.service.ProductService;
import com.example.jsonexercise.service.UserService;
import com.example.jsonexercise.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.jsonexercise.constants.GlobalConstants.RESOURCES_ROOT_PATH;
import static com.example.jsonexercise.constants.GlobalConstants.RESOURCES_ROOT_PATH_OUT;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCTS_FILE_NAME = "products.json";
    private static final String PRODUCTS_IN_RANGE_JSON = "products-in-range.json";
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, CategoryService categoryService, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedProducts() throws IOException {
        if (productRepository.count() > 0) {
            return;
        }

        String fileContent = Files
                .readString(Path.of(RESOURCES_ROOT_PATH + PRODUCTS_FILE_NAME));

        ProductSeedDto[] productSeedDto = gson.fromJson(fileContent, ProductSeedDto[].class);

        Arrays.stream(productSeedDto)
                .filter(validationUtil::isValid)
                .map(product -> {
                    Product productToAdd = modelMapper.map(product, Product.class);
                    productToAdd.setSeller(userService.findRandomUser());
                    if (product.getPrice().compareTo(BigDecimal.valueOf(900)) > 0) {
                        productToAdd.setBuyer(userService.findRandomUser());
                    }

                    Set<Category> categories = categoryService.findRandomCategories();
                    productToAdd.setCategories(categories);

                    return productToAdd;
                })
                .forEach(productRepository::save);

    }

    @Override
    public void saveInFileAllProductsInPriceRangeAndNoBuyerOrderedByPrice() throws IOException {
        Set<Product> productsEntities = productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal.valueOf(500L), BigDecimal.valueOf(1000L));


        List<ProductsInRangeNoBuyer> productsDto = productsEntities.stream()
                        .map(product -> {
                            ProductsInRangeNoBuyer productDto = modelMapper.map(product, ProductsInRangeNoBuyer.class);
                            String sellerFullName = product.getSeller().getFirstName() + " " + product.getSeller().getLastName();
                            productDto.setSellerFullName(sellerFullName);
                            return productDto;
                        })

                                .toList();

        String path = RESOURCES_ROOT_PATH_OUT + PRODUCTS_IN_RANGE_JSON;
        FileWriter writer = new FileWriter(path);

        gson.toJson(productsDto, writer);

        writer.flush();
        writer.close();


    }

}
