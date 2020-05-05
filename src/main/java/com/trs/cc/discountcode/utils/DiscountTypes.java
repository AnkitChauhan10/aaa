package com.trs.cc.discountcode.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum DiscountTypes {

    PERCENT("Percent"),
    AMOUNT("Amount"),
    TICKET("Ticket");  //(Free ticket in discount rule))

    String name;

    DiscountTypes(String name){
        this.name = name;
    }

    public static List<Map<String,String>> toList(){
        return Arrays.stream(values()).map(DiscountTypes::toMap).collect(Collectors.toList());
    }

    Map<String,String> toMap(){
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("value",this.toString());
        return map;
    }

}
