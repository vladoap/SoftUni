package com.example.advquerying.services.impl;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import com.example.advquerying.services.IngredientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public List<String> findAllByNameStartingWith(String name) {
        return ingredientRepository.findAllByNameStartingWith(name)
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllByNameIn(Collection<String> name) {
        return ingredientRepository.findAllByNameInOrderByPrice(name)
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteAllByNameIn(Collection<String> names) {
        ingredientRepository.deleteAllByNameIn(names);
    }

    @Override
    @Transactional
    public void updatePriceByModifier(BigDecimal priceModifier) {
        ingredientRepository.updatePriceByModifier(priceModifier);
    }

    @Override
    @Transactional
    public void updatePriceByModifier(BigDecimal priceModifier, Collection<String> names) {
        ingredientRepository.updatePriceByModifier(priceModifier, names);
    }


}
