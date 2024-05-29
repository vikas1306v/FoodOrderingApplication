package com.food.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    private String itemName;
    private String itemDescription;
    private List<String> itemIngredients;
    private Double itemPrice;
    private String itemImage;
    private Boolean isItemActive;
    private Boolean isItemInStock;
    private Double itemDiscount;
    private String itemDiscountType;//percentage or amount
    private String itemDistributionType;//single or combo

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "category_id")
    private Categories categories;

    @ManyToMany(mappedBy = "items")
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "item")
    @JsonManagedReference
    private List<Review> review;


}
