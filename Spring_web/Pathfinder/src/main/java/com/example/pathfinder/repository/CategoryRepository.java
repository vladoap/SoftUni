package com.example.pathfinder.repository;

import com.example.pathfinder.model.entity.Category;
import com.example.pathfinder.model.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(CategoryNameEnum name);
}
