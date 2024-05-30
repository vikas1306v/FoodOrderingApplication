package com.food.Exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationFailedException extends RuntimeException{
    public AuthenticationFailedException(String message){
        super(message);
    }
}
