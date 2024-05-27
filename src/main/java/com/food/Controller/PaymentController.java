package com.food.Controller;

import com.food.Services.RazorPayPaymentService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController
{
    private final RazorPayPaymentService razorPayPaymentService;
    // This is a dummy method to test the payment service

    @PostMapping("/pay")
    public String pay() throws RazorpayException {
        return razorPayPaymentService.createOrder(10L);
    }
}
