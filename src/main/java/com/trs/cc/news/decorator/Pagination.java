package com.trs.cc.news.decorator;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagination {

	Integer pageNumber;
	Integer pageLimit;



}
