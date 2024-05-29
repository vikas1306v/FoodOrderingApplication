package com.food.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.food.Utils.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_order")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String orderId;
    private LocalDateTime orderDate;
    private LocalDateTime orderDeliveryDate;
    private LocalDateTime orderExpectedDeliveryDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private Double orderTotalAmount;
    private Double orderDiscount;
    private Double orderTax;
    private Double orderFinalAmount;
    private Double deliveryCharge;
    private String orderAddress;
    private Boolean isOrderDelivered;

    @OneToOne(mappedBy = "order")
    private Payment payment;

    @ManyToMany
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    @JsonManagedReference(value = "order-item")
    private Set<Item> items = new HashSet<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;


}
