package com.example.jsonexercise.service;

import com.example.jsonexercise.model.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {


    void seedCategories() throws IOException;

    Set<Category> findRandomCategories();

    void saveInJsonAllCategoriesOrderedByProductCount() throws IOException;
}
