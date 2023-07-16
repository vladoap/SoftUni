package com.example.productshopdatabase.service.impl;

import com.example.productshopdatabase.model.dto.CategoryByProductCountDto;
import com.example.productshopdatabase.model.dto.CategorySeedDto;
import com.example.productshopdatabase.model.dto.CategoryViewRootDto;
import com.example.productshopdatabase.model.entity.Category;
import com.example.productshopdatabase.model.entity.Product;
import com.example.productshopdatabase.repository.CategoryRepository;
import com.example.productshopdatabase.service.CategoryService;
import com.example.productshopdatabase.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;


    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories(List<CategorySeedDto> categories) throws JAXBException, FileNotFoundException {
        categories
                .stream()
                .filter(validationUtil::isValid)
                .map(category -> modelMapper.map(category, Category.class))
                .forEach(categoryRepository::save);
    }

    public long getCount() {
        return categoryRepository.count();
    }

    @Override
    public Set<Category> getRandomCategories() {
        long count = categoryRepository.count();

        Set<Category> randomCategories = new HashSet<>();
        for (int i = 0; i < 2; i++) {
            long rndId = ThreadLocalRandom.current().nextLong(1, count + 1);

            Category category = categoryRepository.findById(rndId).orElse(null);

            randomCategories.add(category);
        }

        return randomCategories;
    }

    @Override
    public CategoryViewRootDto findAllCategoriesOrderedByProductsCount() {
        CategoryViewRootDto categoryViewRootDto = new CategoryViewRootDto();


        List<Category> categories = categoryRepository.findAllCategoriesByProductsCount();

        categoryViewRootDto.setCategories(categories
                .stream()
                .map(category -> {
                    CategoryByProductCountDto categoryDto = modelMapper.map(category, CategoryByProductCountDto.class);

                    categoryDto.setProductCount(category.getProducts().size());
                    categoryDto.setAveragePrice(getAveragePrice(category));
                    categoryDto.setTotalRevenue(getTotalPrice(category));

                    return categoryDto;
                })
                .collect(Collectors.toList()));

        return categoryViewRootDto;
    }

    private static BigDecimal getAveragePrice(Category category) {
        BigDecimal averagePrice = category
                .getProducts()
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(category.getProducts().size()), 2, RoundingMode.HALF_UP);
        return averagePrice;
    }

    private BigDecimal getTotalPrice(Category category) {
        return categoryRepository.getTotalPriceSumByCategoryName(category.getName());
    }
}
