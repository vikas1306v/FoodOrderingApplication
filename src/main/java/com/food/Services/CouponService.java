package com.food.Services;

import com.food.Repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CouponService
{
//    private final CouponRepository couponRepository;
//    private final String couponCode = "GET10";
//
//    public ResponseEntity<?> getTotalPriceAfterCoupon(String couponCode, Double totalPrice)
//    {
//        Map<String ,Double> result=new HashMap<>();
//        switch (couponCode){
//            case "GET10": {
//                Double totalDiscount=totalPrice-(totalPrice*10/100);
//                result.put("totalDiscount",totalDiscount);
//                return new ResponseEntity<>(result, HttpStatus.OK);
//
//            }
//            case "GETFREE":{
//
//            }
//            case "GET$10":{
//
//            }
//        }
//
//        return null;
//    }


}
