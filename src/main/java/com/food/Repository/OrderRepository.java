package com.food.Repository;

import com.food.Entities.Order;
import com.food.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Integer>{

    List<Order> findAllByUser(User user);
}
