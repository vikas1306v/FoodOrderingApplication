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
@CrossOrigin("*")
public class ItemController
{

    private final ItemService itemService;
    @PostMapping("/create/{category_id}")
    public ResponseEntity<GenericResponseBean<Item>> addItem( @PathVariable("category_id") Integer id , @RequestBody CreateItemRequestDto createItemRequestDto) {
        return itemService.createItem(id,createItemRequestDto);
    }
    @GetMapping("/search")
    public ResponseEntity<GenericResponseBean<?>> searchItem(@RequestParam("search") String search,
                                                             @RequestParam(value="page",defaultValue = "1")Integer page,
                                                             @RequestParam(value = "size",defaultValue = "5") Integer size){
        return itemService.searchItem(search,page,size);
    }
    @PostMapping("/make/live/{item_id}")
    public ResponseEntity<GenericResponseBean<Item>> makeItemLive(@PathVariable("item_id") Integer id) {
        return itemService.makeItemLive(id);
    }
    @GetMapping("/alloutofstock")
    public ResponseEntity<GenericResponseBean<?>> getAllOutOfStockItems() {
        return itemService.getAllOutOfStockItems();
    }
    @GetMapping("/all")
    public ResponseEntity<GenericResponseBean<?>> getAllItems() {
        return itemService.getAllItems();
    }


}
