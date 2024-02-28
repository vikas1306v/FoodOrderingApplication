package com.food.Services;

import com.food.Entities.Item;
import com.food.Repository.ItemRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService
{
    private final ItemRepositroy itemRepositroy;
    //create a product
    public ResponseEntity<String> createItem( Item item)
    {
        itemRepositroy.save(item);
        return new ResponseEntity<>("Item created successfully", null, 200);
    }


    //find all the items by category id
    public ResponseEntity<List<Item>> findItemByCategoryId(Integer category_id)
    {
        List<Item> items = itemRepositroy.findItemByCategoryId(category_id);
        return new ResponseEntity<>(items, null, 200);
    }


}
