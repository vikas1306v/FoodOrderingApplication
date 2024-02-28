package com.food.Exception;

public class TokenNotGiven extends RuntimeException
{
    TokenNotGiven()
    {
        super("Token not given");
    }
    public TokenNotGiven(String message)
    {
        super(message);
    }
}
