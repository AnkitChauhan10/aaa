package com.trs.cc.discountcode.decorator;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DiscountCodeResponse {
    String discountCode;
    Date startDate;
    Date expirationDate;
}
