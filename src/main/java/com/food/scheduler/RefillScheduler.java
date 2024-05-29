package com.food.scheduler;

import com.food.Services.InventoryService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@RequiredArgsConstructor
public class RefillScheduler {
    private final InventoryService inventoryService;
    //for every sunday at 12:00 AM
    @Scheduled(cron = "0 0 0 * * SUN")
    public void refillAllOutOfStockItems() {
        inventoryService.refillAllOutOfStockItems();
    }
}
