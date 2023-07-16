package com.example.productshopdatabase.service;

import com.example.productshopdatabase.model.dto.ProductSeedDto;
import com.example.productshopdatabase.model.dto.ProductViewRootDto;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface ProductService {

    long getCount();

    void seedProducts(List<ProductSeedDto> products);

    ProductViewRootDto findAllProductsInRangeWithoutBuyer();
}
