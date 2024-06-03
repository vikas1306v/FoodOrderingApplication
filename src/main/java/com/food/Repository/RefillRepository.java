package com.food.Repository;

import com.food.Entities.RefillValues;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefillRepository extends JpaRepository<RefillValues, Integer> {
}
