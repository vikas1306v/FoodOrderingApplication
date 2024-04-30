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
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ItemService
{
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ImageService imageService;
    
    public ResponseEntity<GenericResponseBean<?>> createItem(MultipartFile multipartFile, Integer categoryId, CreateItemRequestDto createItemRequestDto) {
        
        try{
            String itemImageUrl=imageService.upload(multipartFile);
            Categories category = categoryRepository.findById(categoryId).orElseThrow(() -> new FoodOrderingMainException("Category Not Found With this  id"));
            Item build = Item.builder().isItemActive(false).itemDescription(createItemRequestDto.getItemDescription()).
                    categories(category).isItemInStock(false).itemDiscount(createItemRequestDto.getItemDiscount()).
                    itemDiscountType(createItemRequestDto.getItemDiscountType()).itemImage(itemImageUrl).itemIngredients(createItemRequestDto.getItemIngredients()).itemPrice(createItemRequestDto.getItemPrice()).build();
            itemRepository.save(build);
            return ResponseEntity.status(HttpStatus.CREATED).body(GenericResponseBean.builder().
                    message("Item created Successfully").status(true)
                    .build());
        }catch (Exception e)
        {
            throw new FoodOrderingMainException("Something Went wrong");
        }

    }
}
