package com.food.Controller;

import com.food.Dto.Request.CreateCouponRequestDto;
import com.food.Dto.Response.EligibleCouponResponseDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Entities.Coupon;
import com.food.Services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {
    private final CouponService couponService;
    @PostMapping
    public ResponseEntity<GenericResponseBean<Coupon>> createCoupon(@RequestBody CreateCouponRequestDto createCouponRequestDto) {
        return couponService.createCoupon(createCouponRequestDto);
    }
    @PostMapping("/eligible-coupon/{userId}/{cartId}")
    public ResponseEntity<GenericResponseBean<List<EligibleCouponResponseDto>>>  getEligibleCoupon(@PathVariable Integer userId, @PathVariable Integer cartId) {
        return couponService.checkForEligibleCoupons(userId, cartId);
    }
    @PostMapping("/make-active/{couponId}")
    public ResponseEntity<GenericResponseBean<Coupon>> makeCouponActive(@PathVariable Integer couponId) {
        return couponService.makeCouponActive(couponId);
    }

}
