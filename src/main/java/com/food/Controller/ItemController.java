package com.food.Controller;

import com.food.Entities.Categories;
import com.food.Entities.Item;
import com.food.Repository.CategoryRepository;
import com.food.Services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/item")
public class ItemController
{

    private final ItemService itemService;
    private final CategoryRepository categoryRepository;

    @PostMapping("/create/{category_id}")
    public ResponseEntity<String> addItem(@RequestBody Item item, @PathVariable Integer category_id)
    {
        Categories categories = categoryRepository.findById(category_id).get();
        item.setCategories(categories);
        return itemService.createItem(item);
    }



}
