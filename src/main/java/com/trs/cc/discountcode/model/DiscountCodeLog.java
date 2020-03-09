package com.trs.cc.discountcode.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("discount_code_log")
public class DiscountCodeLog extends PathTrail{
    @Id
    String id;

    String discountCodeId;
    String userId;
    String module;
}
