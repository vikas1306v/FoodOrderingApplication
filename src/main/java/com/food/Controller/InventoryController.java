package com.food.Controller;

import com.food.Dto.Response.CheckQuantityOfProductResponseDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;
    //batch processing to make all out of stock refill
    @PostMapping("/refill")
    public ResponseEntity<GenericResponseBean<?>> refillAllOutOfStockItems() {
        return inventoryService.refillAllOutOfStockItems();
    }

    //check quantity of an items
    @GetMapping("/check/{item_id}")
    public ResponseEntity<GenericResponseBean<CheckQuantityOfProductResponseDto>> checkQuantity(@PathVariable("item_id") Integer id){
        return inventoryService.checkQuantity(id);
    }
}
