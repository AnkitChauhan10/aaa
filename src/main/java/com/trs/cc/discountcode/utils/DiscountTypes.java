package com.trs.cc.discountcode.utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public enum DiscountTypes {

    AMOUNT, PERCENTAGE, QUANTITY;

    public List<DiscountTypes> getDiscountTypes() {
        return Arrays.asList(DiscountTypes.values());
    }

}
