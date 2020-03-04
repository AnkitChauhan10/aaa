package com.trs.cc.news.decorator;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FilterRequest<FILTER> {
    FILTER filter;
    Pagination page;
}
