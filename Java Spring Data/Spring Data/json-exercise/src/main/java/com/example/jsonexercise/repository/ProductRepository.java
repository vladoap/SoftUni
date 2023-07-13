package com.example.jsonexercise.repository;

import com.example.jsonexercise.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Set<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal lower, BigDecimal upper);
}
