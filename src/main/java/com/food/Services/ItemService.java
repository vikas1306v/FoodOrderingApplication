package com.food.Services;

import com.food.Dto.Request.CreateItemRequestDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Entities.Categories;
import com.food.Entities.Item;
import com.food.Exception.FoodOrderingMainException;
import com.food.Repository.CategoryRepository;
import com.food.Repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService
{
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ImageService imageService;
    
    public ResponseEntity<GenericResponseBean<?>> createItem(Integer categoryId, CreateItemRequestDto createItemRequestDto) {
        
        try{

            Categories category = categoryRepository.findById(categoryId).orElseThrow(() -> new FoodOrderingMainException("Category Not Found With this  id"));
            Item build = Item.builder().isItemActive(false).itemDescription(createItemRequestDto.getItemDescription()).
                    categories(category).isItemInStock(false).itemDiscount(createItemRequestDto.getItemDiscount()).
                    itemDiscountType(createItemRequestDto.getItemDiscountType()).itemIngredients(createItemRequestDto.getItemIngredients()).itemPrice(createItemRequestDto.getItemPrice()).build();
            itemRepository.save(build);
            return ResponseEntity.status(HttpStatus.CREATED).body(GenericResponseBean.builder().
                    message("Item created Successfully").status(true)
                    .build());
        }catch (Exception e)
        {
            throw new FoodOrderingMainException("Something Went wrong");
        }

    }

    public ResponseEntity<GenericResponseBean<?>> searchItem(String search)
    {
        List<Item> itemList = itemRepository.searchItem(search);
        System.out.println(itemList);
        return ResponseEntity.status(HttpStatus.OK).body(GenericResponseBean.builder().message("Item Found").status(true).data(itemList).build());
    }
}
