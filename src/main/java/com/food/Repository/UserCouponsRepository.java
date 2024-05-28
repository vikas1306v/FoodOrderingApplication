package com.food.Repository;

import com.food.Entities.UserCoupons;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCouponsRepository extends JpaRepository<UserCoupons, Integer> {
    Optional<UserCoupons> findByUserIdAndCouponId(Integer userId, Integer couponId);
}
