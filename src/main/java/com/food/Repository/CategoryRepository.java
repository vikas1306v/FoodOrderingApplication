package com.food.Repository;

import com.food.Entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {
}
