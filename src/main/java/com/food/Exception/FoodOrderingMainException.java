package com.food.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class FoodOrderingMainException extends RuntimeException{
    public FoodOrderingMainException(String message){
        super(message);
    }
}
