package com.food.Services;

import com.food.Entities.Item;
import com.food.Repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService
{
    private final ItemRepository itemRepository;
    //create a product
    public ResponseEntity<String> createItem( Item item)
    {
        itemRepository.save(item);
        return new ResponseEntity<>("Item created successfully", null, 200);
    }





}
