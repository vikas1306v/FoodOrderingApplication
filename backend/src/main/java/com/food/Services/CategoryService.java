package com.food.Services;

import com.food.Dto.Request.CreateCategoryRequestDto;
import com.food.Dto.Request.UpdateCategoryRequestDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Dto.Response.ItemDetailsResponseDto;
import com.food.Dto.Response.PageBean;
import com.food.Entities.Categories;
import com.food.Entities.Item;
import com.food.Exception.FoodOrderingMainException;
import com.food.Repository.CategoryRepository;
import com.food.Repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService
{

    private final  CategoryRepository categoryRepository;
    private final ImageService imageService;
    private final ItemRepository itemRepository;

    public ResponseEntity<GenericResponseBean<?>> createCategory(CreateCategoryRequestDto createCategoryRequestDto) {
        try {
            Categories category = Categories.builder()
                    .categoryName(createCategoryRequestDto.getCategoryName())
                    .categoryDescription(createCategoryRequestDto.getCategoryDescription())
                    .isCategoryActive(false)
                    .categoryType(createCategoryRequestDto.getCategoryType())
                    .build();
            categoryRepository.save(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(GenericResponseBean.builder().message("Category created successfully").status(true).build());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GenericResponseBean.builder().message("Category creation failed").status(false).build());}
    }

    public ResponseEntity<GenericResponseBean<?>> makeActive(Integer id) {
        if (id == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GenericResponseBean.builder().message("Id cannot be null").status(false).build());
        Categories category = categoryRepository.findById(id).orElseThrow(() -> new FoodOrderingMainException("Category not found with this id"));
        category.setIsCategoryActive(true);
        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.OK).body(GenericResponseBean.builder().message("Category activated successfully").status(true).build());

    }

    @Transactional
    public ResponseEntity<GenericResponseBean<?>> deleteCategory(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(GenericResponseBean.builder().message("Category deleted successfully").status(true).build());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GenericResponseBean.builder().message("Category deletion failed").status(false).build());
        }
    }
    @Transactional
    public ResponseEntity<GenericResponseBean<?>> updateCategory(Integer id, UpdateCategoryRequestDto category)
    {
        try{
            Categories categories = categoryRepository.findById(id).orElseThrow(() -> new FoodOrderingMainException("Category not found with this id"));
            updateCategoryRequestDtoToCategoriesMapper(categories, category);
            categoryRepository.save(categories);
            return ResponseEntity.status(HttpStatus.OK).body(GenericResponseBean.builder().message("Category updated successfully").status(true).build());
        }catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GenericResponseBean.builder().message("Category inundation failed").status(false).build());
        }

    }
    public void updateCategoryRequestDtoToCategoriesMapper(Categories categories, UpdateCategoryRequestDto category)
    {
        if(category.getCategoryName()!=null)
        {
            categories.setCategoryName(category.getCategoryName());
        }
        if(category.getCategoryDescription()!=null)
        {
            categories.setCategoryDescription(category.getCategoryDescription());
        }
        if(category.getCategoryType()!=null)
        {
            categories.setCategoryType(category.getCategoryType());
        }
        if(category.getCategoryImage()!=null)
        {
            categories.setCategoryImage(category.getCategoryImage());
        }
    }


    public ResponseEntity<GenericResponseBean<ItemDetailsResponseDto>> findItemsInCategory(Integer id, Integer pageSize, Integer pageNumber, String sort) {
        if(pageNumber<0)
            pageNumber=0;
        if(pageSize<0)
            pageSize=10;
        if(id==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GenericResponseBean.<ItemDetailsResponseDto>builder().message("Id cannot be null").status(false).build());
        PageRequest pageRequest=null;
        if(sort.equals("asc"))
             pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("itemName").ascending());
        else if(sort.equals("desc"))
            pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("itemName").descending());
        try{
            Categories categories = categoryRepository.findById(id).orElseThrow(() -> new FoodOrderingMainException("Category not found with this id"));
            Page<Item> byCategories = itemRepository.findByCategories(categories, pageRequest);
            List<Item> items = byCategories.getContent();
            PageBean pageBean=new PageBean();
            pageBean.setPageNumber(pageNumber);
            pageBean.setPageSize(pageSize);
            pageBean.setTotalRecords(byCategories.getTotalElements());
            pageBean.setTotalPage(byCategories.getTotalPages());
            List<ItemDetailsResponseDto> itemDetailsResponseDto = new ArrayList<>();
            itemsToItemsDetailsResponseDtoMapper(items, itemDetailsResponseDto);
            return ResponseEntity.status(HttpStatus.OK).body(GenericResponseBean.<ItemDetailsResponseDto>builder().result(itemDetailsResponseDto)
                    .page(pageBean).message("Items found successfully").status(true).build());
        }
       catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GenericResponseBean.<ItemDetailsResponseDto>builder().message("Category not found").status(false).build());
       }
    }

    private void itemsToItemsDetailsResponseDtoMapper(List<Item> items, List<ItemDetailsResponseDto> itemDetailsResponseDto) {
        items.forEach(item -> {
            ItemDetailsResponseDto itemDetailsResponseDto1 = ItemDetailsResponseDto.builder()
                    .itemName(item.getItemName())
                    .itemDescription(item.getItemDescription())
                    .itemIngredients(item.getItemIngredients())
                    .itemPrice(item.getItemPrice())
                    .itemImage(item.getItemImage())
                    .isItemActive(item.getIsItemActive())
                    .isItemInStock(item.getIsItemInStock())
                    .itemDiscount(item.getItemDiscount())
                    .itemDiscountType(item.getItemDiscountType())
                    .itemDistributionType(item.getItemDistributionType())
                    .build();
            itemDetailsResponseDto.add(itemDetailsResponseDto1);
        });
    }

    public ResponseEntity<GenericResponseBean<?>> findAllItemsByCategory(Integer categoryId) {
        Categories categories = categoryRepository.findByCategoryId(categoryId).orElseThrow(() -> new FoodOrderingMainException("Category Not Found"));
        List<Item> items=categories.getItem();
        return ResponseEntity.status(HttpStatus.OK).body(
                GenericResponseBean.builder().status(true).message("Items Found").data(items).build()
        );
    }

    public ResponseEntity<GenericResponseBean<?>> findAllCategories() {
        List<Categories> categories = categoryRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                GenericResponseBean.builder().status(true).message("Items Found").data(categories).build()
        );
    }
}
