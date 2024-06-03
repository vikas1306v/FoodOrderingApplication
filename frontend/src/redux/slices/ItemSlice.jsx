
import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  items:null
};

const itemSlice = createSlice({
  name: 'item',
  initialState,
  reducers: {
    addItems(state, action) {
      state.items=action.payload.data
    },
    removeAllItems(state){
      state.items=null
    }
  },
});

export const { addItems,removeAllItems} = itemSlice.actions;
export default itemSlice.reducer;
