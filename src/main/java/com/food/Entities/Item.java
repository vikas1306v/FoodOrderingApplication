package com.food.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="item")
public class Item
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer item_id;
    private String item_name;
    private String item_description;
    private String item_price;
    private String item_image;
    private Boolean item_active;
    private Boolean is_item_in_stock;
    private Integer item_quantity;
    private Integer item_discount;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories categories;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order_id;
    @ManyToOne
    private Cart  cart;
    @OneToMany
    private List<Review> review;





}
