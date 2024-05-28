package com.food.Controller;

import com.food.Dto.Request.CreateOrderRequestDto;
import com.food.Services.OrderService;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/create-order")
    public Order createOrder(@RequestBody CreateOrderRequestDto createOrderRequestDto) throws RazorpayException {
        return orderService.createOrder(createOrderRequestDto);
    }
}
