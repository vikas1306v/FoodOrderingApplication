package com.food.Services;

import com.food.Entities.Categories;
import com.food.Entities.Item;
import com.food.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryService
{

    private final  CategoryRepository categoryRepository;

    //create category
    public ResponseEntity<Map<String ,String>> createCategory(Categories category)
    {
        Map<String ,String> response = new HashMap<>();
        try
        {
            categoryRepository.save(category);
            response.put("message", "Category created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch(Exception e)
        {
            response.put("message", "Category creation failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //delete a category
    public ResponseEntity<?> deleteCategory(Integer id)
    {
        Map<String ,String> response = new HashMap<>();
        try
        {
            categoryRepository.deleteById(id);
            response.put("message", "Category deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch(Exception e)
        {
            response.put("message", "Category deletion failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //update a  category

    public ResponseEntity<?> updateCategory(Integer id, Categories category)
    {
        Map<String ,String> response = new HashMap<>();
        try
        {
            Categories categories = categoryRepository.findById(id).get();
            categories.setC_name(category.getC_name()!=null?category.getC_name():categories.getC_name());
            categories.setC_description(category.getC_description()!=null?category.getC_description():categories.getC_description());
            categories.setC_image(category.getC_image()!=null?category.getC_image():categories.getC_image());
            categoryRepository.save(categories);
            response.put("message", "Category updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch(Exception e)
        {
            response.put("message", "Category updation failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //find all the items in a category
    public ResponseEntity<?> findItemsInCategory(Integer id)
    {
        Map<String ,List<Item>> response = new HashMap<>();
        try
        {
            Categories categories = categoryRepository.findById(id).get();
            List<Item> items = categories.getItem();

            response.put("items", items);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch(Exception e)
        {
            response.put("message",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
