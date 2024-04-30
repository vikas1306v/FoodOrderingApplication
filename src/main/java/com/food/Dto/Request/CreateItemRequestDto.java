package com.food.Dto.Request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateItemRequestDto {
    @JsonProperty("item_name")
    private String itemName;

    @JsonProperty("item_description")
    private String itemDescription;

    @JsonProperty("item_ingredients")
    private List<String> itemIngredients;

    @JsonProperty("item_price")
    private BigDecimal itemPrice;

    @JsonProperty("item_discount")
    private Double itemDiscount;

    @JsonProperty("item_discount_type")
    private String itemDiscountType; //percentage or amount

    @JsonProperty("item_distribution_type")
    private String itemDistributionType; //single or combo
}
