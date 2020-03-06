package com.trs.cc.discountcode.decorator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UseDiscountCodeRequest {
    String userId;
    String discountCode;
}
