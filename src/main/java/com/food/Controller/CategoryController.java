package com.food.Controller;

import com.food.Dto.Request.CreateCategoryRequestDto;
import com.food.Dto.Request.UpdateCategoryRequestDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Dto.Response.ItemDetailsResponseDto;
import com.food.Services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController
{
    private final CategoryService categoryService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericResponseBean<?>> create(@RequestBody() CreateCategoryRequestDto createCategoryRequestDto) {
        return categoryService.createCategory( createCategoryRequestDto);
    }
    @PostMapping("/makeCategoryActive")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericResponseBean<?>> makeActive(@RequestParam("category_id") Integer id) {
        return categoryService.makeActive(id);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericResponseBean<?>> delete(@RequestParam("category_id") Integer id) {
        return categoryService.deleteCategory(id);
    }

    @PatchMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenericResponseBean<?>> update(@RequestParam("category_id") Integer id, @RequestBody UpdateCategoryRequestDto category) {
        return categoryService.updateCategory(id, category);
    }

    @GetMapping("/find/items")
    public ResponseEntity<GenericResponseBean<ItemDetailsResponseDto>> findItemsByCategory(@RequestParam("category_id") Integer id,
    @RequestParam(value = "page_size",defaultValue = "10") Integer pageSize,
    @RequestParam(value = "page_number",defaultValue = "0") Integer pageNumber,@RequestParam(value = "sort",defaultValue = "asc") String sort) {
        return categoryService.findItemsInCategory(id, pageSize, pageNumber, sort);
    }


}
