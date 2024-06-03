package com.food.Services;

import com.food.Entities.Review;
import com.food.Repository.ItemRepository;
import com.food.Repository.ReviewRepository;
import com.food.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService
{
//    private final ReviewRepository reviewRepository;
//    private final UserRepository  userRepository;
//    private final ItemRepository  itemRepository;
//
//    //adding review of a item
//    public ResponseEntity<?> addReview(int itemId, int userId, Review   review)
//    {
//        if(userRepository.existsById(userId) && itemRepository.existsById(itemId)) {
//            review.setItem(itemRepository.findById(itemId).get());
//            review.setUser(userRepository.findById(userId).get());
//            return new ResponseEntity<>(reviewRepository.save(review), HttpStatus.OK);
//        }else{
//            return  new ResponseEntity<>("something wrong went",HttpStatus.NOT_FOUND);
//        }



}
