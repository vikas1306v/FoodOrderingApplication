package com.food.Controller;

import com.food.Dto.Request.CreateCartRequestDto;
import com.food.Dto.Response.AddToCartResponseDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    @PostMapping("/add-to-cart")
    public ResponseEntity<GenericResponseBean<AddToCartResponseDto>> addToCart(@RequestBody CreateCartRequestDto createCartRequestDto) {
        return cartService.addToCart(createCartRequestDto);
    }
    @DeleteMapping("/remove-from-cart")
    public ResponseEntity<GenericResponseBean<AddToCartResponseDto>> removeFromCart(@RequestBody CreateCartRequestDto createCartRequestDto) {
        return cartService.removeFromCart(createCartRequestDto);
    }
    @PostMapping("/apply-coupon/{couponCode}/{cartId}")
    public ResponseEntity<GenericResponseBean<AddToCartResponseDto>> applyCoupon(@PathVariable String couponCode,@PathVariable Integer cartId) {
        return cartService.applyCoupon(couponCode,cartId);
    }
    @DeleteMapping("/remove-coupon/{couponCode}/{cartId}")
    public ResponseEntity<GenericResponseBean<AddToCartResponseDto>> removeCoupon(@PathVariable String couponCode,@PathVariable Integer cartId) {
        return cartService.removeCoupon(couponCode,cartId);
    }


 }
