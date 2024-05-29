package com.food.Repository;

import com.food.Entities.Categories;
import com.food.Entities.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer>, ItemRepositoryCustom
{

    Page<Item> findByCategories(Categories category, Pageable pageable);
    List<Item> findAllByIsItemInStock(boolean b);
}
