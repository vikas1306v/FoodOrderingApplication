package com.food.Controller;

import com.food.Dto.Request.CreateItemRequestDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Entities.Categories;
import com.food.Entities.Item;
import com.food.Repository.CategoryRepository;
import com.food.Services.ImageService;
import com.food.Services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController
{

    private final ItemService itemService;
    @PostMapping("/create/{category_id}")
    public ResponseEntity<GenericResponseBean<Item>> addItem( @PathVariable("category_id") Integer id , @RequestBody CreateItemRequestDto createItemRequestDto) {
        return itemService.createItem(id,createItemRequestDto);
    }
    @GetMapping("/search")
    public ResponseEntity<GenericResponseBean<?>> searchItem(@RequestParam("search") String search) {
        return itemService.searchItem(search);
    }
    @PostMapping("/make/live/{item_id}")
    public ResponseEntity<GenericResponseBean<Item>> makeItemLive(@PathVariable("item_id") Integer id) {
        return itemService.makeItemLive(id);
    }
    @GetMapping("/alloutofstock")
    public ResponseEntity<GenericResponseBean<?>> getAllOutOfStockItems() {
        return itemService.getAllOutOfStockItems();
    }


}
