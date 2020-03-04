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

	public enum types {AMOUNT, PERCENTAGE, QUANTITY}

	@Id
	String id;

	String discountCode;
	String name;
	String description;
	types discount_type;
	float discount_value;
	List<Roles> roles;
	
}
