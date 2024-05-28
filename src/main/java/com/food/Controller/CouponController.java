package com.food.Controller;

import com.food.Dto.Request.CreateCouponRequestDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Entities.Coupon;
import com.food.Services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {
    private final CouponService couponService;
    @PostMapping
    public ResponseEntity<GenericResponseBean<Coupon>> createCoupon(@RequestBody CreateCouponRequestDto createCouponRequestDto) {
        return couponService.createCoupon(createCouponRequestDto);
    }
}
