package com.food.Exception;

public class TokenNotGiven extends RuntimeException
{
    TokenNotGiven() {
        super();}
    public TokenNotGiven(String message)
    {
        super(message);
    }
}
