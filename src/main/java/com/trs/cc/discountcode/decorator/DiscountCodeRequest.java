package com.trs.cc.discountcode.decorator;

import com.trs.cc.discountcode.utils.DiscountTypes;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DiscountCodeRequest {
    String discountCode;
    String name;
    String description;
    DiscountTypes discountType;
    Float discountValue;
    Float maxDiscountLowerLimit;
    Integer noOfMaxUsage;
    Date startDate;
    Date expirationDate;
    List<String> modules;
    List<String> users;
}
