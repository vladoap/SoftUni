package com.example.productshopdatabase.service;

import com.example.productshopdatabase.model.dto.CategorySeedDto;
import com.example.productshopdatabase.model.dto.CategoryViewRootDto;
import com.example.productshopdatabase.model.entity.Category;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

public interface CategoryService {


    void seedCategories(List<CategorySeedDto> categories) throws JAXBException, FileNotFoundException;

    long getCount();

    Set<Category> getRandomCategories();

    CategoryViewRootDto findAllCategoriesOrderedByProductsCount();

}
