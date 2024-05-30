package com.food.Repository;

import com.food.Entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {


    Optional<Coupon> findByCouponCode(String couponCode);
}
