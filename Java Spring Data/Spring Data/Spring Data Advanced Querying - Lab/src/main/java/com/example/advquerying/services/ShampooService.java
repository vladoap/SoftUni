package com.example.advquerying.services;

import com.example.advquerying.entities.Size;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface ShampooService {

     List<String> findAllBySizeOrderById(Size size);

     List<String> findAllBySizeOrLabelIdOrderByPrice(Size size, Long labelId);

     List<String> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

     Integer countShampooByPriceLessThan(BigDecimal price);

     List<String> findAllByIngredientsWithNamesIn(Collection<String> names);

     List<String> findAllByByIngredientsCountLessThan(Long count);
}