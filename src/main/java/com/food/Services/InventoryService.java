package com.food.Services;

import com.food.Dto.Response.CheckQuantityOfProductResponseDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Entities.Item;
import com.food.Entities.ItemHistory;
import com.food.Entities.RefillValues;
import com.food.Repository.ItemHistoryRepository;
import com.food.Repository.ItemRepository;
import com.food.Repository.RefillRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final ItemRepository itemRepository;
    private final ItemHistoryRepository itemHistoryRepository;
    private final RefillRepository  refillRepository;
    public ResponseEntity<GenericResponseBean<?>> refillAllOutOfStockItems() {
        List<Item> allOutOfStockItems = itemRepository.findAllByIsItemInStock(false);
        allOutOfStockItems.forEach(item -> {
            item.setIsItemInStock(true);
            itemRepository.save(item);
            ItemHistory itemHistory = itemHistoryRepository.findByItemId(item.getItemId());
            itemHistory.setItemQuantity(itemHistory.getItemQuantity() + 100);
            itemHistory.setItemRefillCount(itemHistory.getItemRefillCount() + 1);
            RefillValues refillValues = new RefillValues();
            refillValues.setRefillValue(100);
            refillValues.setItemHistory(itemHistory);
            refillValues.setRefillDateAndTime(new java.sql.Timestamp(System.currentTimeMillis()).toLocalDateTime());
            refillRepository.save(refillValues);
            itemHistory.getRefillValuesList().add(refillValues);
            itemHistoryRepository.save(itemHistory);
        });
        return ResponseEntity.status(200).body(GenericResponseBean.builder().message("All out of stock items refilled").build());
    }

    public ResponseEntity<GenericResponseBean<CheckQuantityOfProductResponseDto>> checkQuantity(Integer id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null) {
            return ResponseEntity.status(404).body(GenericResponseBean.<CheckQuantityOfProductResponseDto>builder().message("Item not found").build());
        }
        ItemHistory itemHistory = itemHistoryRepository.findByItemId(id);
        return ResponseEntity.status(200).body(GenericResponseBean.<CheckQuantityOfProductResponseDto>builder().data(CheckQuantityOfProductResponseDto.builder().name(item.getItemName()).quantity(itemHistory.getItemQuantity()).build()).message("Item quantity checked").build());
    }
}
