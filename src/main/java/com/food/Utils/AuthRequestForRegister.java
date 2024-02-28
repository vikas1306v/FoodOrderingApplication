package com.food.Utils;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthRequestForRegister
{
    private String email;
    private String password;
    private String address;


}
