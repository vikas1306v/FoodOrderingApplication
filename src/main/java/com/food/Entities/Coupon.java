package com.food.Entities;

import com.food.Utils.CouponType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coupon
{
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer couponId;
    private String couponName;
    private String couponCode;
    private String couponDescription;
    private Double couponDiscount;
    @Enumerated(EnumType.STRING)
    private CouponType couponType;

    @ManyToMany(mappedBy = "coupons")
    private Set<User> users = new HashSet<>();


}
