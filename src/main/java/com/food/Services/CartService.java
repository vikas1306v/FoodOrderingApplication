package com.food.Services;

import com.food.Dto.Request.CreateCartRequestDto;
import com.food.Dto.Response.AddToCartResponseDto;
import com.food.Dto.Response.GenericResponseBean;
import com.food.Dto.Response.ItemDetailsResponseDto;
import com.food.Entities.Cart;
import com.food.Entities.CartItem;
import com.food.Entities.Item;
import com.food.Entities.User;
import com.food.Exception.FoodOrderingMainException;
import com.food.Repository.*;
import com.food.Utils.CouponDiscountType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CartItemRepository cartItemRepository;
    private final CouponRepository couponRepository;
    @Transactional
    public ResponseEntity<GenericResponseBean<AddToCartResponseDto>> addToCart(CreateCartRequestDto createCartRequestDto) {
        User user= userRepository.findById(createCartRequestDto.getUserId()).orElseThrow(()->new FoodOrderingMainException("User not found"));
        Item item= itemRepository.findById(createCartRequestDto.getItemId()).orElseThrow(() -> new FoodOrderingMainException("Item not found"));
        cartRepository.findByUser(user).ifPresentOrElse(cart -> {
            CartItem cartItem1 = cartItemRepository.findByItemId(createCartRequestDto.getItemId()).orElse(null);
            //check if item already in cart
            if(cartItem1!=null){
                cartItem1.setQuantity(cartItem1.getQuantity()+createCartRequestDto.getQuantity());
                cartItemRepository.save(cartItem1);
                double cartOldTotal=cart.getCartTotal();
                double cartTotalAmount = calculateCartTotalAmount(item, createCartRequestDto.getQuantity());
                cartTotalAmount+=cartOldTotal;
                cart.setCartTotal(cartTotalAmount);
                double cartOldDiscount=cart.getTotalCartDiscount();
                double cartTotalDiscount = calculateCartTotalDiscount(item, createCartRequestDto.getQuantity());
                cartTotalDiscount+=cartOldDiscount;
                cart.setTotalCartDiscount(cartTotalDiscount);
                cart.setCartSize(cart.getCartSize()+1);
                cart.getCartItems().add(cartItem1);
                cart.setCartTotalAfterTax(cart.getCartTotal()+cart.getCartTax());
                cartRepository.save(cart);
                return;
            }
            //new item in cart
            CartItem cartItem= CartItem.builder().itemId(item.getItemId()).carts(new HashSet<>()).
                    quantity(createCartRequestDto.getQuantity()).build();
            cart.setCartSize(cart.getCartSize()+1);
            double cartOldTotal=cart.getCartTotal();
            double cartTotalAmount = calculateCartTotalAmount(item, createCartRequestDto.getQuantity());
            cartTotalAmount+=cartOldTotal;
            cart.setCartTotal(cartTotalAmount);
            double cartOldDiscount=cart.getTotalCartDiscount();
            double cartTotalDiscount = calculateCartTotalDiscount(item, createCartRequestDto.getQuantity());
            cartTotalDiscount+=cartOldDiscount;
            cart.setTotalCartDiscount(cartTotalDiscount);
            cart.getCartItems().add(cartItem);
            cart.setCartTotalAfterTax(cart.getCartTotal()+cart.getCartTax());
            cartRepository.save(cart);
        }, () -> {
            //new cart
            Cart cart=new Cart();
            CartItem cartItem= CartItem.builder().itemId(item.getItemId()).quantity(createCartRequestDto.getQuantity())
                    .carts(new HashSet<>()).build();
            cart.setCartItems(new HashSet<>());
            cart.getCartItems().add(cartItem);
            cart.setCartSize(1);
            cart.setCartTax(10.0);
            double cartTotalAmount = calculateCartTotalAmount(item, createCartRequestDto.getQuantity());
            cart.setCartTotal(cartTotalAmount);
            double cartTotalDiscount = calculateCartTotalDiscount(item, createCartRequestDto.getQuantity());
            cart.setTotalCartDiscount(cartTotalDiscount);
            cart.setUser(user);
            cart.setCartTotalAfterTax(cart.getCartTotal()+cart.getCartTax());
            cartRepository.save(cart);
        });
        Cart cart = cartRepository.findByUser(user).get();
        return getResponse(cart);
    }


    private @NotNull ResponseEntity<GenericResponseBean<AddToCartResponseDto>> getResponse(Cart cart) {
        List<ItemDetailsResponseDto> itemDetailsResponseDtoList=new ArrayList<>();
        AddToCartResponseDto addToCartResponseDto=new AddToCartResponseDto();
        addToCartResponseDto.setCartId(cart.getCartId());
        addToCartResponseDto.setCartTotal(cart.getCartTotal());
        addToCartResponseDto.setCartTax(cart.getCartTax());
        addToCartResponseDto.setCouponAmount(cart.getCouponAmount());
        addToCartResponseDto.setCartTotalAfterTax(cart.getCartTotalAfterTax());
        addToCartResponseDto.setCartTotalDiscountAfterApplyingCoupon(cart.getCartTotalDiscountAfterApplyingCoupon());
        addToCartResponseDto.setCartTotalAfterApplyingCoupon(cart.getCartTotalAfterApplyingCoupon());
        addToCartResponseDto.setTotalCartDiscount(cart.getTotalCartDiscount());
        addToCartResponseDto.setCartSize(cart.getCartSize());
        addToCartResponseDto.setCartTotalAfterApplyingCoupon(cart.getCartTotalAfterApplyingCoupon());
        cart.getCartItems().forEach((cartItem)->{
            Item item1 = itemRepository.findById(cartItem.getItemId()).get();
            ItemDetailsResponseDto itemDetailsResponseDto=new ItemDetailsResponseDto();
            itemDetailsResponseDto.setItemName(item1.getItemName());
            itemDetailsResponseDto.setItemPrice(item1.getItemPrice());
            itemDetailsResponseDto.setItemDiscount(item1.getItemDiscount());
            itemDetailsResponseDto.setItemQuantity(cartItem.getQuantity());
            itemDetailsResponseDto.setIsItemInStock(item1.getIsItemInStock());
            itemDetailsResponseDtoList.add(itemDetailsResponseDto);
        });
        addToCartResponseDto.setItemDetailsResponseDtoList(itemDetailsResponseDtoList);
        return ResponseEntity.status(HttpStatus.CREATED).body(GenericResponseBean.<AddToCartResponseDto>builder().data(addToCartResponseDto).build());
    }

    public double calculateCartTotalAmount(Item item, int quantity)
    {
        double oneItemPrice=item.getItemPrice();
        double oneItemDiscount=item.getItemDiscount();
        return (oneItemPrice-oneItemDiscount)*quantity;
    }
    public double calculateCartTotalDiscount(Item item, int quantity)
    {
        double oneItemDiscount=item.getItemDiscount();
        return oneItemDiscount*quantity;
    }

    @Transactional
    public ResponseEntity<GenericResponseBean<AddToCartResponseDto>> removeFromCart(CreateCartRequestDto createCartRequestDto) {
        User user= userRepository.findById(createCartRequestDto.getUserId()).orElseThrow(()->new FoodOrderingMainException("User not found"));
        Item item= itemRepository.findById(createCartRequestDto.getItemId()).orElseThrow(() -> new FoodOrderingMainException("Item not found"));
        Cart cart= cartRepository.findByUser(user).orElseThrow(()->new FoodOrderingMainException("Cart not found"));
        CartItem cartItem1 = cartItemRepository.findByItemId(createCartRequestDto.getItemId()).orElseThrow(()->new FoodOrderingMainException("Item not found in cart"));
        if(cartItem1.getQuantity()>createCartRequestDto.getQuantity()){
            cartItem1.setQuantity(cartItem1.getQuantity()-createCartRequestDto.getQuantity());
            cartItemRepository.save(cartItem1);
            double cartOldTotal=cart.getCartTotal();
            double cartTotalAmount = calculateCartTotalAmount(item, createCartRequestDto.getQuantity());
            cartTotalAmount=cartOldTotal-cartTotalAmount;
            cart.setCartTotal(cartTotalAmount);
            double cartOldDiscount=cart.getTotalCartDiscount();
            double cartTotalDiscount = calculateCartTotalDiscount(item, createCartRequestDto.getQuantity());
            cartTotalDiscount=cartOldDiscount-cartTotalDiscount;
            cart.setTotalCartDiscount(cartTotalDiscount);
            cart.setCartSize(cart.getCartSize()-1);
            cart.getCartItems().add(cartItem1);
            cartRepository.save(cart);
        }
        else if(cartItem1.getQuantity().equals(createCartRequestDto.getQuantity())){
            cartItemRepository.delete(cartItem1);
            double cartOldTotal=cart.getCartTotal();
            double cartTotalAmount = calculateCartTotalAmount(item, createCartRequestDto.getQuantity());
            cartTotalAmount=cartOldTotal-cartTotalAmount;
            cart.setCartTotal(cartTotalAmount);
            double cartOldDiscount=cart.getTotalCartDiscount();
            double cartTotalDiscount = calculateCartTotalDiscount(item, createCartRequestDto.getQuantity());
            cartTotalDiscount=cartOldDiscount-cartTotalDiscount;
            cart.setTotalCartDiscount(cartTotalDiscount);
            cart.setCartSize(cart.getCartSize()-1);
            cart.getCartItems().add(cartItem1);
            cartRepository.save(cart);
        }
        else{
            throw new FoodOrderingMainException("Quantity to remove is greater than quantity in cart");
        }
        if(cart.getCartSize()==0){
            cartRepository.delete(cart);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(GenericResponseBean.<AddToCartResponseDto>builder().message("Cart is empty").build());
        }
        return getResponse(cart);
    }

    public ResponseEntity<GenericResponseBean<AddToCartResponseDto>> applyCoupon(String couponCode, Integer cartId) {
        cartRepository.findById(cartId).ifPresentOrElse(cart -> {
            //apply coupon
            couponRepository.findByCouponCode(couponCode).ifPresentOrElse(coupon -> {
                double cartTotal=cart.getCartTotal();
                double cartTotalDiscount=cart.getTotalCartDiscount();
                double cartTotalAfterApplyingCoupon=cartTotal;
                double cartTotalDiscountAfterApplyingCoupon=cartTotalDiscount;
                if(coupon.getCouponDiscountType()==CouponDiscountType.PERCENTAGE){
                    cartTotalAfterApplyingCoupon=cartTotal-(cartTotal*coupon.getCouponDiscount()/100);
                    cartTotalDiscountAfterApplyingCoupon=cartTotalDiscount-(cartTotal*coupon.getCouponDiscount()/100);
                }
                else if(coupon.getCouponDiscountType()==CouponDiscountType.AMOUNT){
                    cartTotalAfterApplyingCoupon=cartTotal-coupon.getCouponDiscount();
                    cartTotalDiscountAfterApplyingCoupon=cartTotalDiscount-coupon.getCouponDiscount();
                }
                cart.setCartTotalAfterApplyingCoupon(cartTotalAfterApplyingCoupon);
                cart.setCartTotalDiscountAfterApplyingCoupon(cartTotalDiscountAfterApplyingCoupon);
                cart.setCouponAmount(coupon.getCouponDiscount());
                cartRepository.save(cart);
            }, () -> {
                throw new FoodOrderingMainException("Coupon not found");
            });
        }, () -> {
            throw new FoodOrderingMainException("Cart not found");
        });
        Cart cart = cartRepository.findById(cartId).get();
        return getResponse(cart);
    }

    public ResponseEntity<GenericResponseBean<AddToCartResponseDto>> removeCoupon(String couponCode, Integer cartId) {
        cartRepository.findById(cartId).ifPresentOrElse(cart -> {
            couponRepository.findByCouponCode(couponCode).ifPresentOrElse(coupon -> {
                cart.setCartTotalAfterApplyingCoupon(0.0);
                cart.setCartTotalDiscountAfterApplyingCoupon(0.0);
                cart.setCouponAmount(0.0);
                cartRepository.save(cart);
            }, () -> {
                throw new FoodOrderingMainException("Coupon not found");
            });
        }, () -> {
            throw new FoodOrderingMainException("Cart not found");
        });
        Cart cart = cartRepository.findById(cartId).get();
        return getResponse(cart);
    }
}
