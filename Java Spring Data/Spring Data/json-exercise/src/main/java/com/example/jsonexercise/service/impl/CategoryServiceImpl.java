package com.example.jsonexercise.service.impl;

import com.example.jsonexercise.model.Category;
import com.example.jsonexercise.model.Product;
import com.example.jsonexercise.model.dto.CategoryByProductsCount;
import com.example.jsonexercise.model.dto.CategorySeedDto;
import com.example.jsonexercise.repository.CategoryRepository;
import com.example.jsonexercise.service.CategoryService;
import com.example.jsonexercise.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.jsonexercise.constants.GlobalConstants.RESOURCES_ROOT_PATH;
import static com.example.jsonexercise.constants.GlobalConstants.RESOURCES_ROOT_PATH_OUT;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORIES_FILE_NAME = "categories.json";
    private static final String CATEGORIES_BY_PRODUCTS_NAME = "categories-by-products.json";
    private final CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.categoryRepository = categoryRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() > 0) {
            return;
        }

        String fileContent = Files
                .readString(Path.of(RESOURCES_ROOT_PATH + CATEGORIES_FILE_NAME));

        CategorySeedDto[] categorySeedDto = gson.fromJson(fileContent, CategorySeedDto[].class);

        Arrays.stream(categorySeedDto)
                .filter(validationUtil::isValid)
                .map(category -> modelMapper.map(category, Category.class))
                .forEach(categoryRepository::save);


    }

    @Override
    public Set<Category> findRandomCategories() {
        int countCat = ThreadLocalRandom.current().nextInt(1, 3);
        long totalCategories = categoryRepository.count();
        Set<Category> categories = new HashSet<>();

        for (int i = 0; i < countCat; i++) {
            long rndCatId = ThreadLocalRandom.current().nextLong(1, totalCategories + 1);
            Category rndCat = categoryRepository.findById(rndCatId).orElse(null);
            categories.add(rndCat);
        }

        return categories;
    }

    @Override
    public void saveInJsonAllCategoriesOrderedByProductCount() throws IOException {

        Set<CategoryByProductsCount> categoryDto = categoryRepository.findAllByOrderByProductsSize();


        String jsonContent = gson.toJson(categoryDto);

        Files.write(Path.of(RESOURCES_ROOT_PATH_OUT + CATEGORIES_BY_PRODUCTS_NAME),
                Collections.singleton(jsonContent));


    }
}
