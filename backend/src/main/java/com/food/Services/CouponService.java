package com.food.Services;

import com.food.Dto.Request.CreateCouponRequestDto;
import com.food.Dto.Response.EligibleCouponResponseDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Entities.*;
import com.food.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CouponService
{
    private final CouponRepository couponRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final UserCouponsRepository userCouponsRepository;
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
        return couponRepository.save(coupon);
    }
    public ResponseEntity<GenericResponseBean<List<EligibleCouponResponseDto>>> checkForEligibleCoupons(Integer userId,Integer cartId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Cart not found"));
        List<Order> orderList = orderRepository.findAllByUser(user);
        List<Coupon> couponList = couponRepository.findAll();
        couponList.removeIf(coupon -> !coupon.isActive());
        List<Coupon>  eligibleCoupon=new ArrayList<>();
        if(!couponList.isEmpty()){
            eligibleCoupon=couponList.stream().map((coupon)-> createEligibleCouponList(coupon,user,cart,orderList)).
                       filter(Objects::nonNull).toList();
        }
        List<EligibleCouponResponseDto> eligibleCouponResponseDto = new ArrayList<>();
        return createResponse(eligibleCoupon, eligibleCouponResponseDto);
    }

    private ResponseEntity<GenericResponseBean<List<EligibleCouponResponseDto>>> createResponse(List<Coupon> eligibleCoupon, List<EligibleCouponResponseDto> eligibleCouponResponseDto) {
       if(eligibleCoupon.isEmpty()) {
           return ResponseEntity.status(HttpStatus.OK).
                   body(GenericResponseBean.<List<EligibleCouponResponseDto>>builder().
                           status(false).
                           message("No eligible coupons found").build());
       }else{
           eligibleCoupon.forEach(coupon -> {
               EligibleCouponResponseDto eligibleCouponResponseDto1 = new EligibleCouponResponseDto();
               eligibleCouponResponseDto1.setCouponName(coupon.getCouponName());
               eligibleCouponResponseDto1.setId(coupon.getId());
               eligibleCouponResponseDto1.setCouponCode(coupon.getCouponCode());
               eligibleCouponResponseDto1.setCouponDescription(coupon.getCouponDescription());
               eligibleCouponResponseDto1.setCouponDiscount(coupon.getCouponDiscount());
               eligibleCouponResponseDto1.setCouponDiscountType(coupon.getCouponDiscountType());
               eligibleCouponResponseDto1.setCouponType(coupon.getCouponType());
               eligibleCouponResponseDto1.setUsageLimit(coupon.getUsageLimit());
               eligibleCouponResponseDto1.setMinCartAmount(coupon.getMinCartAmount());
               eligibleCouponResponseDto.add(eligibleCouponResponseDto1);
           });
              return ResponseEntity.status(HttpStatus.OK).
                     body(GenericResponseBean.<List<EligibleCouponResponseDto>>builder().
                            status(true).
                            message("Eligible coupons found").data(eligibleCouponResponseDto).build());
       }
    }
    private Coupon createEligibleCouponList(Coupon coupon, User user, Cart cart, List<Order> orderList) {
        switch (coupon.getCouponType()) {
            case NewUser:
                if(orderList.isEmpty()){
                    Double totalAmount = cart.getCartTotal();
                    if(totalAmount>=coupon.getMinCartAmount()){
                        return coupon;
                    }
                }
                break;
            case CartAmountSpecific:
                System.out.println("hello");
                Double totalAmount = cart.getCartTotal();
                boolean ans=false;
                UserCoupons userCoupons = userCouponsRepository.findByUserIdAndCouponId(user.getId(), coupon.getId()).orElse(null);
                if(userCoupons!=null) {
                    if (userCoupons.getCouponUsageCount() < coupon.getUsageLimit()) {
                        ans = true;
                    }
                }else{
                    ans=true;
                }
                if(totalAmount>=coupon.getMinCartAmount()&&ans){
                    return coupon;
                }
                break;
        }
        return null;
    }

    public ResponseEntity<GenericResponseBean<Coupon>> makeCouponActive(Integer couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new RuntimeException("Coupon not found"));
        coupon.setActive(true);
        Coupon save = couponRepository.save(coupon);
        return ResponseEntity.status(HttpStatus.OK).body(GenericResponseBean.<Coupon>builder().data(save).message("Coupon activated successfully").build());
    }
}
