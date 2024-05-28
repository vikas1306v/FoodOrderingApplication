package com.food.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefillValues {
    private Integer refillId;
    private Integer refillValue;
    private LocalDateTime refillDateAndTime;
    @ManyToOne
    private ItemHistory itemHistory;

}
