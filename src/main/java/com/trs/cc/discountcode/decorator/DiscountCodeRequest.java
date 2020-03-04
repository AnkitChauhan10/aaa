package com.trs.cc.discountcode.decorator;

import com.trs.cc.discountcode.model.DiscountCodeAPI;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountCodeRequest {
    String discountCode;
    String name;
    String description;
    DiscountCodeAPI.types discount_type;
    float discount_value;
}
