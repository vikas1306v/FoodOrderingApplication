package com.food.Dto.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddToCartResponseDto {
    List<ItemDetailsResponseDto> itemDetailsResponseDtoList;
    private Integer cartId;
    private Double cartTotal;
    private Integer cartSize;
    private Double totalCartDiscount;
    private Double cartTax;
    private Double cartTotalAfterTax;
    private Double cartTotalAfterApplyingCoupon;
    private Double cartTotalDiscountAfterApplyingCoupon;
    private Double couponAmount;



}
