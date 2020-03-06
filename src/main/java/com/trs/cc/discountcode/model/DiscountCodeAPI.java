package com.trs.cc.discountcode.model;

import com.trs.cc.discountcode.utils.Roles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("discount_code_apis")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DiscountCodeAPI extends PathTrail {
	@Id
	String id;

	String name;

	String description;

	List<Roles> roles;
}
