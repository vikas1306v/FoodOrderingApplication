package com.food.Entities;

import com.food.Utils.CouponDiscountType;
import com.food.Utils.CouponType;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coupon
{
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;
    private String couponName;
    private String couponCode;
    private String couponDescription;
    private Double couponDiscount;
    private CouponDiscountType couponDiscountType;
    @Enumerated(EnumType.STRING)
    private CouponType couponType;
    private Integer usageLimit;
    private boolean isActive;
    private Double minCartAmount;

//    @ManyToMany(mappedBy = "coupons")
//    private Set<User> users = new HashSet<>();


}
