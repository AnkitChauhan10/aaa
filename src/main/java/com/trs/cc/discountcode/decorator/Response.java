package com.trs.cc.discountcode.decorator;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@JsonRootName ( value = "status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response{
	HttpStatus code;
	String status;
	String description;
}
