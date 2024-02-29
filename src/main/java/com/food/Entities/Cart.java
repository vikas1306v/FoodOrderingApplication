package com.food.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer_cart")
public class Cart
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cart_Id;
    @OneToOne()
    @JoinColumn(name = "customer_id")
    private User customer;
    @OneToMany(mappedBy = "cart")
    private List<Item> items;
    private Integer total;
    private Integer cart_size;
    private Integer total_cart_discount;
}
