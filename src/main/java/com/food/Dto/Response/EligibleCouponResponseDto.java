package com.food.Dto.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.food.Utils.CouponDiscountType;
import com.food.Utils.CouponType;
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
    private Integer id;
    private String couponName;
    private String couponCode;
    private String couponDescription;
    private Double couponDiscount;
    private CouponDiscountType couponDiscountType;
    private CouponType couponType;
    private Integer usageLimit;
    private String itemIds;
    private Double minCartAmount;
}
