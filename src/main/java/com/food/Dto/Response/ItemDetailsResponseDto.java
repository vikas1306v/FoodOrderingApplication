package com.food.Dto.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDetailsResponseDto
{

    private String itemName;
    private String itemDescription;
    private List<String> itemIngredients;
    private Double itemPrice;
    private String itemImage;
    private Boolean isItemActive;
    private Boolean isItemInStock;
    private Integer itemQuantity;
    private Double itemDiscount;
    private String itemDiscountType;//percentage or amount
    private String itemType;//veg or non-veg
    private String itemDistributionType;//single or combo
}
