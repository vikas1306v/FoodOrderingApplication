import { createSlice } from '@reduxjs/toolkit';

const initialState = {

      itemDetailsResponseDtoList: [],
      cartId: null,
      cartTotal: null,
      cartSize: null,
      totalCartDiscount: null,
      cartTax: null,
      cartTotalAfterTax: null,
    
  };

  const cartSlice = createSlice({
    name: 'cart',
    initialState,
    reducers: {
      addItemToCart(state, action) {
        state.itemDetailsResponseDtoList.push(action.payload.itemDetailsResponseDtoList);
        state.cartSize = action.payload.cartSize;
        state.cartTotal = action.payload.cartTotal;
        state.cartTotalAfterTax =  action.payload.cartTotalAfterTax;
        state.cartId = action.payload.cartId;
        state.totalCartDiscount = action.payload.totalCartDiscount;
        state.cartTax = action.payload.cartTax;
      },
      removeItemFromCart(state, action) {
        const itemIndex = state.itemDetailsResponseDtoList.findIndex(
          (item) => item.itemId === action.payload.itemId
        );
        state.cartSize = state.itemDetailsResponseDtoList.length;
        state.cartTotal -= state.itemDetailsResponseDtoList[itemIndex].itemPrice;
        state.cartTotalAfterTax = state.cartTotal + state.cartTax - state.totalCartDiscount;
        state.itemDetailsResponseDtoList.splice(itemIndex, 1);
      },
      
    },
  });


export const { addItemToCart, removeItemFromCart } = cartSlice.actions;
export default cartSlice.reducer;
