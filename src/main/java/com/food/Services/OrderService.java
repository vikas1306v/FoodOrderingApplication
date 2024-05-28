package com.food.Services;

import com.food.Dto.Request.CreateOrderRequestDto;
import com.food.Utils.OrderStatus;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Value("${razorpay.keyId}")
    private String RAZORPAY_KEY;
    @Value("${razorpay.keySecret}")
    private String RAZORPAY_SECRET;


    public Order createOrder(CreateOrderRequestDto createOrderRequestDto) throws RazorpayException {
        RazorpayClient razorpayClient = new RazorpayClient(RAZORPAY_KEY, RAZORPAY_SECRET);
        com.food.Entities.Order order = new com.food.Entities.Order();
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.CREATED);

        Double amount = createOrderRequestDto.getOrderAmount();
        JSONObject ob = new JSONObject();
        ob.put("amount", amount*100);
        ob.put("currency", "INR");
        Order orderRazorPay = razorpayClient.orders.create(ob);

        return orderRazorPay;

    }


}
