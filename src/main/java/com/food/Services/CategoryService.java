package com.food.Services;

import com.food.Entities.Categories;
import com.food.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

}
