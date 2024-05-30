package com.food.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_payment")
public class Payment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    private String paymentType;//credit card, debit card, net banking, UPI, cash on delivery
    private String paymentStatus;//success, failed, pending
    private LocalDate paymentDate;
    private LocalTime paymentTime;

    @OneToOne
    private Order order;


}
