package com.example.jsonexercise.service;

import java.io.IOException;

public interface ProductService {

    void seedProducts() throws IOException;

    void saveInFileAllProductsInPriceRangeAndNoBuyerOrderedByPrice() throws IOException;

}
