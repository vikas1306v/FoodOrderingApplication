package com.food.Dto.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponseDto
{
    @JsonProperty("token")
    private  String token;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("user_name")
    private String name;
    @JsonProperty("user_image_url")
    private String imageUrl;
    @JsonProperty("user_email")
    private String email;

}
