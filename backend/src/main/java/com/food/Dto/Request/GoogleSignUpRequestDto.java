package com.food.Dto.Request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GoogleSignUpRequestDto
{

    private String email;
    private String name;
    private String imageUrl;
}
