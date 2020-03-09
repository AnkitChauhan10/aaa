package com.trs.cc.discountcode.decorator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountCodeUseRequest {
    String userId;
    String discountCode;
}
