package com.trs.cc.discountcode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PathTrail {

	@JsonIgnore
	public String createdBy;
	@JsonIgnore
	public Date created;
	@JsonIgnore
	public String updatedBy;
	@JsonIgnore
	public Date updated;
	
}
