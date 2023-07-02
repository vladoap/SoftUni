package com.example.advquerying.services.impl;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import com.example.advquerying.services.ShampooService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<String> findAllBySizeOrderById(Size size) {
        return shampooRepository.findAllBySizeOrderById(size)
                .stream()
                .map(Shampoo::toString)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBySizeOrLabelIdOrderByPrice(Size size, Long labelId) {
        return shampooRepository.findAllBySizeOrLabelIdOrderByPrice(size, labelId)
                .stream()
                .map(Shampoo::toString)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price) {
        return shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price)
                .stream()
                .map(Shampoo::toString)
                .collect(Collectors.toList());
    }

    @Override
    public Integer countShampooByPriceLessThan(BigDecimal price) {
        return shampooRepository.countShampooByPriceLessThan(price);
    }

    @Override
    public List<String> findAllByIngredientsWithNamesIn(Collection<String> names) {
        return shampooRepository.findAllByIngredientsWithNamesIn(names)
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllByByIngredientsCountLessThan(Long count) {
        return shampooRepository.findAllByByIngredientsCountLessThan(count)
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toList());
    }

}
