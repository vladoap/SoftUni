package com.example.javaspringexercise.service;

import com.example.javaspringexercise.model.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {

    void seedCategories() throws IOException;

    Set<Category> getRandomCategories();
}
