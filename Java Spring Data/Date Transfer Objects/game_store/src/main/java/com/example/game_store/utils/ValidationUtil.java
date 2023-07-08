package com.example.game_store.utils;

import jakarta.validation.ConstraintViolation;

import java.util.Set;

public interface ValidationUtil {

    <E> Set<ConstraintViolation<E>> violation (E entity);

    <E> boolean isValid (E entity);
}
