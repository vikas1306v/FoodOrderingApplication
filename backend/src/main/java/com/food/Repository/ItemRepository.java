package com.food.Repository;

import com.food.Entities.Categories;
import com.food.Entities.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer>
{

    Page<Item> findByCategories(Categories category, Pageable pageable);
    List<Item> findAllByIsItemInStock(boolean b);
    @Query("SELECT i FROM Item i WHERE LOWER(i.itemName) LIKE LOWER(concat('%', :search, '%'))")
    Page<Item> findByItemNameContaining(String search, Pageable pageable);
}
