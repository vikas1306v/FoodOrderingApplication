package com.food.Controller;

import com.food.Entities.Categories;
import com.food.Services.CategoryService;
import com.food.Services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController
{
    private final CategoryService categoryService;
    private final ImageService imageService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String,String>> create( @RequestParam("category_name") String category_name,
                                                      @RequestParam("category_description") String category_description,
                                                      @RequestParam("file") MultipartFile multipartFile)
    {
        String fileName = imageService.upload(multipartFile);
        Categories build = Categories.builder().
                c_name(category_name).
                c_description(category_description).
                c_image(fileName).build();
        return categoryService.createCategory(build);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        return categoryService.deleteCategory(id);
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Categories category)
    {
        if(id==null || category==null)
        {
            return ResponseEntity.badRequest().body("Invalid request Id or category is null");

        }
        return categoryService.updateCategory(id, category);
    }

    //find items by category
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findItemsByCategory(@PathVariable Integer id)
    {
        return categoryService.findItemsInCategory(id);
    }

}
