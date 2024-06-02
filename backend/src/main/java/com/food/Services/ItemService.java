package com.food.Services;

import com.food.Dto.Request.CreateItemRequestDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Dto.Response.PageBean;
import com.food.Entities.Categories;
import com.food.Entities.Item;
import com.food.Entities.ItemHistory;
import com.food.Exception.FoodOrderingMainException;
import com.food.Repository.CategoryRepository;
import com.food.Repository.ItemHistoryRepository;
import com.food.Repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final ItemHistoryRepository itemHistoryRepository;
    @Transactional
    public ResponseEntity<GenericResponseBean<Item>> createItem(Integer categoryId, CreateItemRequestDto createItemRequestDto) {
        try{
            if(createItemRequestDto.getItemQuantity()==null)
                createItemRequestDto.setItemQuantity(100);
            Categories category = categoryRepository.findById(categoryId).orElseThrow(() -> new FoodOrderingMainException("Category Not Found With this  id"));
            Item build = Item.builder().isItemActive(false).
                    itemName(createItemRequestDto.getItemName()).
                    isItemInStock(false).
                    itemImage(createItemRequestDto.getItemImage()).
                    isItemInStock(true).
                    itemDescription(createItemRequestDto.getItemDescription()).
                    categories(category).
                    itemDiscount(createItemRequestDto.getItemDiscount()).
                    itemDiscountType(createItemRequestDto.getItemDiscountType()).
                    itemIngredients(createItemRequestDto.getItemIngredients()).
                    itemPrice(createItemRequestDto.getItemPrice()).build();
            Item saved = itemRepository.save(build);
            ItemHistory itemHistory = ItemHistory.builder().
                    itemId(saved.getItemId()).
                    itemQuantity(createItemRequestDto.getItemQuantity()).
                    itemOrderCount(0).
                    itemRefillCount(0).
                    itemId(build.getItemId()).build();
            itemHistoryRepository.save(itemHistory);
            return ResponseEntity.status(HttpStatus.CREATED).body(GenericResponseBean.<Item>builder().
                    message("Item created Successfully").status(true)
                            .data(build)
                    .build());
        }catch (Exception e)
        {
            throw new FoodOrderingMainException("Something Went wrong");
        }
    }

    public ResponseEntity<GenericResponseBean<?>> searchItem(String search, Integer page, Integer size) {
        if (page < 0) page = 0;
        if (size <= 0) size = 5;

        PageRequest pageRequest = PageRequest.of(page-1, size);
        Page<Item> itemPage = itemRepository.findByItemNameContaining(search, pageRequest);
        List<Item> itemList = itemPage.getContent();
        if(page>itemPage.getTotalPages())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenericResponseBean.builder().message("Item Not Found").status(false).build());

        PageBean pageBean = new PageBean();
        pageBean.setPageNumber(page);
        pageBean.setPageSize(size);
        pageBean.setTotalRecords(itemPage.getTotalElements());
        pageBean.setTotalPage(itemPage.getTotalPages());

        GenericResponseBean<List<Item>> responseBean = new GenericResponseBean<>();
        responseBean.setPage(pageBean);

        if (!itemList.isEmpty()) {
            responseBean.setData(itemList);
            responseBean.setMessage("Item Found");
            responseBean.setStatus(true);
            return ResponseEntity.status(HttpStatus.OK).body(responseBean);
        } else {
            responseBean.setMessage("Item Not Found");
            responseBean.setStatus(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBean);
        }
    }


    public ResponseEntity<GenericResponseBean<Item>> makeItemLive(Integer id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new FoodOrderingMainException("Item Not Found"));
        if(item.getIsItemInStock())
        {
            item.setIsItemActive(true);
            itemRepository.save(item);
            return ResponseEntity.status(HttpStatus.OK).body(GenericResponseBean.<Item>builder().message("Item is now live").status(true).data(item).build());
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GenericResponseBean.<Item>builder().message("Item is not in stock").status(false).build());
        }
    }

    public ResponseEntity<GenericResponseBean<?>> getAllOutOfStockItems() {
        List<Item> itemList = itemRepository.findAllByIsItemInStock(false);
        if(!itemList.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(GenericResponseBean.builder().message("Item Found").status(true).data(itemList).build());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenericResponseBean.builder().message("Item Not Found").status(false).build());
    }


    public ResponseEntity<GenericResponseBean<?>> getAllItems() {
        List<Item> itemList = itemRepository.findAll();
        if(!itemList.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(GenericResponseBean.builder().message("Item Found").status(true).data(itemList).build());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GenericResponseBean.builder().message("Item Not Found").status(false).build());
    }
}
