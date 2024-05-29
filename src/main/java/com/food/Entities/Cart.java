package com.food.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
    private Double cartTotal;
    private Integer cartSize;
    private Double totalCartDiscount;
    private Double cartTax;
    private Double cartTotalAfterTax;
    private Double cartTotalAfterApplyingCoupon;
    private Double cartTotalDiscountAfterApplyingCoupon;
    private Double couponAmount;

    @OneToOne
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "cart_items_mapping",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_item_id")
    )
    @JsonManagedReference(value = "cart-item")
    private Set<CartItem> cartItems = new HashSet<>();

}
