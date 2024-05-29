package com.food.Repository;

import com.food.Entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem ,Integer> {
    Optional<CartItem> findByItemId(Integer itemId);
}
