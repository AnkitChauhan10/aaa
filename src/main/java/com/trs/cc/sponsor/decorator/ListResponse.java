package com.trs.cc.sponsor.decorator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ListResponse<T> {

	List<T> data;
	Response status;

	public ListResponse() {
		// Define Blank List incase of error we don't send null in status[data]
		data = new ArrayList<>();
	}
}
