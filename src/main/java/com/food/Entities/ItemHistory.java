package com.food.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer itemId;
    private Integer itemQuantity;
    //number of times item refilled
    private Integer itemRefillCount;
    //number of time item ordered
    private Integer itemOrderCount;
    //list of values of every time refill
    @OneToMany(mappedBy = "itemHistory")
    @JsonManagedReference
    private List<RefillValues> refillValuesList;

}
