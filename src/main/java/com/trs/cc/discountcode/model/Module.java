package com.trs.cc.discountcode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("module")
public class Module {
    @Id
    String id;

    String name;

    @JsonIgnore
    boolean softDelete;
}
