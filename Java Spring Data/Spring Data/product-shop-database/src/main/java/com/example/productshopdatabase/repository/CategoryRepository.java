package com.example.productshopdatabase.repository;

import com.example.productshopdatabase.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c ORDER BY size(c.products) DESC")
    List<Category> findAllCategoriesByProductsCount();

    @Query("SELECT sum(p.price) FROM Category c JOIN c.products p WHERE c.name = :name")
    BigDecimal getTotalPriceSumByCategoryName(String name);

}
