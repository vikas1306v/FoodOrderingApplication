
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
  },
});

export const { addItems} = itemSlice.actions;
export default itemSlice.reducer;
