package com.example.productshopdatabase.service.impl;

import com.example.productshopdatabase.model.dto.ProductSeedDto;
import com.example.productshopdatabase.model.dto.ProductViewRootDto;
import com.example.productshopdatabase.model.dto.ProductWithoutBuyerDto;
import com.example.productshopdatabase.model.entity.Product;
import com.example.productshopdatabase.repository.ProductRepository;
import com.example.productshopdatabase.service.CategoryService;
import com.example.productshopdatabase.service.ProductService;
import com.example.productshopdatabase.service.UserService;
import com.example.productshopdatabase.util.ValidationUtil;
import com.example.productshopdatabase.util.XMLParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XMLParser xmlParser;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, UserService userService, ModelMapper modelMapper, ValidationUtil validationUtil, XMLParser xmlParser) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }


    @Override
    public long getCount() {
        return productRepository.count();
    }

    @Override
    public void seedProducts(List<ProductSeedDto> products) {
        products
                .stream()
                .filter(validationUtil::isValid)
                .map(product -> {
                    Product productToAdd = modelMapper.map(product, Product.class);
                    productToAdd.setSeller(userService.getRandomUser());
                    if (productToAdd.getPrice().compareTo(BigDecimal.valueOf(800L)) > 0) {
                        productToAdd.setBuyer(userService.getRandomUser());
                    }
                    productToAdd.setCategories(categoryService.getRandomCategories());
                    return productToAdd;
                })
                .forEach(productRepository::save);
    }

    @Override
    public ProductViewRootDto findAllProductsInRangeWithoutBuyer() {

        ProductViewRootDto productViewRootDto = new ProductViewRootDto();

        productViewRootDto
                .setProducts(productRepository.findAllByPriceBetweenAndBuyerIsNull(BigDecimal.valueOf(500L), BigDecimal.valueOf(1000L))
                        .stream()
                        .map(p -> {
                            ProductWithoutBuyerDto product = modelMapper.map(p, ProductWithoutBuyerDto.class);
                            product.setSellerFullName(String.format("%s%s%s",
                                    p.getSeller().getFirstName() != null
                                            ? p.getSeller().getFirstName()
                                            : "",
                                    p.getSeller().getFirstName() == null
                                            ? ""
                                            : " ",
                                    p.getSeller().getLastName()));

                            return product;
                        })
                        .collect(Collectors.toList()));

        return productViewRootDto;
    }


}
