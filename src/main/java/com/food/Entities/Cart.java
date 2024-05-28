package com.food.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private Integer totalCartDiscount;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart")
    @JsonManagedReference
    private List<Item> item;

}
