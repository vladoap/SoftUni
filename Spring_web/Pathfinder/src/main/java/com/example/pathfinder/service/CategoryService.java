package com.example.pathfinder.service;

import com.example.pathfinder.model.entity.Category;
import com.example.pathfinder.model.enums.CategoryNameEnum;

public interface CategoryService {
    Category findByCategoryName(CategoryNameEnum categoryNameEnum);
}
