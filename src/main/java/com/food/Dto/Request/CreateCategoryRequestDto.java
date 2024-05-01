package com.food.Dto.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCategoryRequestDto
{
    @JsonProperty("category_name")
    private String categoryName;
    @JsonProperty("category_description")
    private String categoryDescription;
    @JsonProperty("category_image")
    private String categoryImage;
    @JsonProperty("category_type")
    private String categoryType;//veg or non-veg


}
