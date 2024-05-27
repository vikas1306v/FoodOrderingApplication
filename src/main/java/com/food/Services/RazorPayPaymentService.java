package com.food.Services;

import com.food.Dto.Request.PaymentLinkResponse;
import com.razorpay.Order;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RazorPayPaymentService
{
    @Value("${razorpay.keyId}")
    private String RAZORPAY_KEY;
    @Value("${razorpay.keySecret}")
    private String RAZORPAY_SECRET;

//    public ResponseEntity<PaymentLinkResponse> createPaymentLink(Long orderId) throws RazorpayException {
//
//        RazorpayClient razorpayClient = new RazorpayClient(RAZORPAY_KEY, RAZORPAY_SECRET);
//        JSONObject ob = new JSONObject();
//        ob.put("amount", amount*100);
//        ob.put("currency", "INR");
//        ob.put("receipt", "rec_1234");
//        Order order = razorpayClient.orders.create(ob);
//        PaymentLinkResponse paymentLinkResponse = new PaymentLinkResponse();
//        paymentLinkResponse.setOrderId(order.get("id"));
//        return ResponseEntity.ok(paymentLinkResponse);
//    }

    public String createOrder(Long amount) throws RazorpayException {
        RazorpayClient razorpayClient = new RazorpayClient(RAZORPAY_KEY, RAZORPAY_SECRET);
        JSONObject ob = new JSONObject();
        ob.put("amount", amount*100);
        ob.put("currency", "INR");
        PaymentLink paymentLink = razorpayClient.paymentLink.create(ob);
//        Order order = razorpayClient.orders.create(ob);
        return paymentLink.get("short_url").toString();
    }
}
