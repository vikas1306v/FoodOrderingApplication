package com.food.Services;

import com.food.Dto.Request.CreateOrderRequestDto;
import com.food.Entities.Cart;
import com.food.Entities.Item;
import com.food.Entities.User;
import com.food.Exception.FoodOrderingMainException;
import com.food.Repository.CartRepository;
import com.food.Repository.UserRepository;
import com.food.Utils.CouponDiscountType;
import com.food.Utils.OrderStatus;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final PincodeService pincodeService;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    @Value("${razorpay.keyId}")
    private String RAZORPAY_KEY;
    @Value("${razorpay.keySecret}")
    private String RAZORPAY_SECRET;


    public Order createOrder(CreateOrderRequestDto createOrderRequestDto) throws RazorpayException {
        User user=userRepository.findById(createOrderRequestDto.getUserId()).orElseThrow(()->new FoodOrderingMainException("User not found"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new FoodOrderingMainException("Cart not found"));
        RazorpayClient razorpayClient = new RazorpayClient(RAZORPAY_KEY, RAZORPAY_SECRET);
        com.food.Entities.Order order = new com.food.Entities.Order();
        //check if pincode is active and delivery is possible
        boolean deliverable=checkForPincodeService(createOrderRequestDto.getPincodeNumber());
        if(!deliverable){
            throw new FoodOrderingMainException("Delivery is not possible to this pincode");
        }
        order.setOrderDate(LocalDateTime.now());
        order.setDeliveryCharge(10.0);
        order.setOrderStatus(OrderStatus.CREATED);
        Double orderTax=10.0;
        order.setOrderTotalAmount(cart.getCartTotal());
        double orderFinalAmount = cart.getCartTotal() + order.getDeliveryCharge();
        order.setOrderTax(orderTax);
        CouponDiscountType couponDiscountType = createOrderRequestDto.getCouponsApplied().getCouponDiscountType();
//        orderDiscount = switch (couponDiscountType) {
//            case PERCENTAGE ->
//                    createOrderRequestDto.getOrderAmount() * createOrderRequestDto.getCouponsApplied().getCouponDiscount() / 100;
//            case AMOUNT -> createOrderRequestDto.getCouponsApplied().getCouponDiscount();
//        };
//
//
//        Double amount = createOrderRequestDto.getOrderAmount();
//        JSONObject ob = new JSONObject();
//        ob.put("amount", amount*100);
//        ob.put("currency", "INR");
//        Order orderRazorPay = razorpayClient.orders.create(ob);
//
        return null;

    }

    private boolean checkForPincodeService(String pincodeNumber) {
        return pincodeService.checkForPincodeDeliverable(pincodeNumber);
    }


}
