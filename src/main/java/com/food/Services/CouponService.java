package com.food.Services;

import com.food.Dto.Request.CreateCouponRequestDto;
import com.food.Dto.Response.EligibleCouponResponseDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Entities.Coupon;
import com.food.Entities.Order;
import com.food.Repository.CouponRepository;
import com.food.Repository.OrderRepository;
import com.food.Utils.CouponDiscountType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CouponService
{
    private final CouponRepository couponRepository;
    private final OrderRepository orderRepository;
    public ResponseEntity<GenericResponseBean<Coupon>> createCoupon(CreateCouponRequestDto createCouponRequestDto) {
        Coupon coupon = new Coupon();
        switch (createCouponRequestDto.getCouponType()) {
            case NewUser:
                createCouponRequestDto.setUsageLimit(1);
                if(createCouponRequestDto.getCouponCode()==null)
                    createCouponRequestDto.setCouponCode("NEWUSER");
                coupon=createCoupon(createCouponRequestDto,coupon);
                break;
            case CartAmountSpecific:
                if(createCouponRequestDto.getCouponCode()==null)
                    createCouponRequestDto.setCouponCode("CART"+createCouponRequestDto.getMinCartAmount());
                coupon=createCoupon(createCouponRequestDto,coupon);
                break;
            case ItemSpecific:
                if(createCouponRequestDto.getCouponCode()==null)
                    createCouponRequestDto.setCouponCode("ITEM"+createCouponRequestDto.getItemIds());
                coupon=createCoupon(createCouponRequestDto,coupon);
                break;
        }
       return ResponseEntity.status(HttpStatus.CREATED).body(GenericResponseBean.<Coupon>builder().data(coupon).message("Coupon created successfully").build());
    }

    private Coupon createCoupon(CreateCouponRequestDto createCouponRequestDto,Coupon coupon) {
        coupon.setCouponDescription(createCouponRequestDto.getCouponDescription());
        coupon.setCouponName(createCouponRequestDto.getCouponName());
        coupon.setCouponCode(createCouponRequestDto.getCouponCode());
        coupon.setCouponDiscount(createCouponRequestDto.getCouponDiscount());
        coupon.setCouponDiscountType(createCouponRequestDto.getCouponDiscountType());
        coupon.setCouponType(createCouponRequestDto.getCouponType());
        coupon.setUsageLimit(createCouponRequestDto.getUsageLimit());
        coupon.setActive(false);
        if(createCouponRequestDto.getMinCartAmount()!=null)
            coupon.setMinCartAmount(createCouponRequestDto.getMinCartAmount());
        if(createCouponRequestDto.getItemIds()!=null)
            coupon.setItemIds(createCouponRequestDto.getItemIds());
        return couponRepository.save(coupon);
    }
    public ResponseEntity<GenericResponseBean<List<EligibleCouponResponseDto>>> checkForEligibleCoupons(Integer userId,Integer cartId) {
        return null;
    }

}
