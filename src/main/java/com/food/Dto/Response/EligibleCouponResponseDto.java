package com.food.Dto.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EligibleCouponResponseDto {
    private String couponName;
    private String couponCode;
    private String couponDescription;
    private Double couponDiscount;
    private String couponDiscountType;
    private String couponType;
    private Integer usageLimit;
    private String itemIds;
    private Double minCartAmount;
}
