package com.food.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException
{
    @ExceptionHandler(TokenNotGiven.class)
    public ResponseEntity<String> tokenNotGiven(TokenNotGiven e)
    {
        return ResponseEntity.status(401).body(e.getMessage());
    }
}
