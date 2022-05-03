package com.springmongo.wrappergetOffers.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderBy {

    private  String field;
    private String order;
    private List<String> value;

}
