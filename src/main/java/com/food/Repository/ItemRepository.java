package com.food.Repository;

import com.food.Entities.Categories;
import com.food.Entities.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer>
{

Page<Item> findByCategories(Categories category, Pageable pageable);

}
