package com.food.Exception;

public class UserAlreadyExist extends RuntimeException{
    public UserAlreadyExist(String message)
    {
        super(message);
    }

}
