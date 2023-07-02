package com.example.advquerying.repositories;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    Set<Shampoo> findAllBySizeOrderById(Size size);

    Set<Shampoo> findAllBySizeOrLabelIdOrderByPrice(Size size, Long id);

    Set<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    Integer countShampooByPriceLessThan(BigDecimal price);

    @Query("SELECT s FROM Shampoo s JOIN s.ingredients i WHERE i.name IN :names")
    Set<Shampoo> findAllByIngredientsWithNamesIn(Collection<String> names);

    @Query("SELECT s FROM Shampoo s JOIN s.ingredients GROUP BY s.id HAVING COUNT(*) < :count")
    Set<Shampoo> findAllByByIngredientsCountLessThan(Long count);




}
