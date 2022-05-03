package com.springmongo.wrappergetOffers.model.msdmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class OrderBy {

    private  String field;
    private String order;
    private List<String> value;

}
