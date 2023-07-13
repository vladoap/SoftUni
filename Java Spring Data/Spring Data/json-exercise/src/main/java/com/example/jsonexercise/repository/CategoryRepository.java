package com.example.jsonexercise.repository;

import com.example.jsonexercise.model.Category;
import com.example.jsonexercise.model.dto.CategoryByProductsCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT new com.example.jsonexercise.model.dto.CategoryByProductsCount(c.name, count(*), avg(p.price), sum(p.price))" +
            " FROM Category c JOIN c.products p GROUP BY c.id ORDER BY count(*) DESC")
    Set<CategoryByProductsCount> findAllByOrderByProductsSize();

}
