import {configureStore} from '@reduxjs/toolkit';
import userSlice from './slices/UserSlice';
import cartSlice from "./slices/CartSlice";
import itemSlice from "./slices/ItemSlice"
import storage from 'redux-persist/lib/storage';
import { persistReducer, persistStore } from 'redux-persist';
import { combineReducers } from '@reduxjs/toolkit';


const persistConfig = {
  key: 'root',
  storage,
};
const reducer=combineReducers({
  user:userSlice,
  item:itemSlice,
  cart:cartSlice
})
const persistedReducer = persistReducer(persistConfig, reducer)

export const store = configureStore({
    reducer: persistedReducer,

  })
  
export const persistor = persistStore(store)