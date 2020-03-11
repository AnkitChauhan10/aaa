package com.trs.cc.discountcode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trs.cc.discountcode.utils.DiscountTypes;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document("discount_code")
public class DiscountCode extends PathTrail {

    @Id
    String id;

    String discountCode;
    String name;
    String description;
    DiscountTypes discountType;
    float discountValue;
    float maxDiscountLowerLimit;
    int noOfMaxUsage;
    int usageCount;
    Date startDate;
    Date expirationDate;
    List<String> modules;
    List<String> users;
    @JsonIgnore
    boolean softDelete;
}
