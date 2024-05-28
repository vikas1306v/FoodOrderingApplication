package com.food.Utils;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum CouponType
{
    NewUser,
    CartAmountSpecific,
    ItemSpecific,

}
