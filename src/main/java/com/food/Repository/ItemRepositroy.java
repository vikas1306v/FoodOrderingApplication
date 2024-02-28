package com.food.Repository;

import com.food.Entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepositroy  extends JpaRepository<Item, Integer>
{

  @Query("from Item as i where i.categories.categoryId = :category_id")
  public List<Item> findItemByCategoryId(@Param("category_id") Integer category_id);
}
