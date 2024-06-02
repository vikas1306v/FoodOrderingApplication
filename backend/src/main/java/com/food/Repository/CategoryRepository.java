package com.food.Repository;

import com.food.Entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {
    Optional<Categories> findByCategoryId(Integer categoryId);
}
