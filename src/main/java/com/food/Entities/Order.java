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
    private LocalDate orderDate;
    private LocalDate orderDeliveryDate;
    private LocalDate orderExpectedDeliveryDate;
    private LocalTime orderTime;
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

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<Item> items ;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;


}
