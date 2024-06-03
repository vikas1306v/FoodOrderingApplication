package com.food.Controller;

import com.food.Entities.Review;
import com.food.Services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
//    @PostMapping("/add/{itemId}/{userId}")
//    public ResponseEntity<?> addReview(@PathVariable int itemId, @PathVariable int userId
//    , @RequestBody Review review)
//    {
//        return reviewService.addReview(itemId,userId,review);
//    }
}
