package com.food.Utils;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthRequestForLogin
{
    private String email;
    private String password;
}
