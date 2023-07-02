package com.example.advquerying.services;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface IngredientService {

    List<String> findAllByNameStartingWith(String name);

    List<String> findAllByNameIn(Collection<String> name);

    void deleteAllByNameIn(Collection<String> names);

    void updatePriceByModifier(BigDecimal priceModifier);

    void updatePriceByModifier(BigDecimal priceModifier, Collection<String> names);
}
