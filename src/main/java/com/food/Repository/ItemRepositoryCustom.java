package com.food.Repository;

import com.food.Entities.Item;

import java.util.List;

public interface ItemRepositoryCustom {
    List<Item> searchItem(String name);
 }
