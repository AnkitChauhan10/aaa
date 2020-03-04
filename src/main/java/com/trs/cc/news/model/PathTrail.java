package com.trs.cc.news.model;

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

	public String createdBy;
	public Date created;
	public String updatedBy;
	public Date updated;
	
}
