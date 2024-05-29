package com.food.Repository;

import com.food.Entities.ItemHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemHistoryRepository extends JpaRepository<ItemHistory, Integer> {
    ItemHistory findByItemId(Integer itemId);
}
