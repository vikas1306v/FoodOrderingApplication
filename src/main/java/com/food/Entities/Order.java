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
@Table(name="customer_order")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;
    private String order_date;
    private String order_status;
    private String order_amount;
    private String order_address;
    private Boolean is_order_delivered;
    private String order_payment_method;
    private String order_payment_status;
    @OneToOne
    private Payment payment;
    @OneToMany(mappedBy = "order_id")
    private List<Item> item;


}
