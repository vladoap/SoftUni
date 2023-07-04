package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Set<Ingredient> findAllByNameStartingWith(String name);

    Set<Ingredient> findAllByNameInOrderByPrice(Collection<String> name);

    @Query("DELETE FROM Ingredient i WHERE i.name IN :names")
    @Modifying
    void deleteAllByNameIn(Collection<String> names);

    @Query("UPDATE Ingredient i SET i.price = i.price * :priceModifier")
    @Modifying
    void updatePriceByModifier(BigDecimal priceModifier);


    @Query("UPDATE Ingredient i SET i.price = i.price * :priceModifier WHERE i.name IN :names")
    @Modifying
    void updatePriceByModifier(BigDecimal priceModifier, Collection<String> names);
}
