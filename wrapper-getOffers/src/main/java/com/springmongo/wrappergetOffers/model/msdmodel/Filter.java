package com.springmongo.wrappergetOffers.model.msdmodel;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Filter {

    private String field;
    private List<String> value;
    private String type= "in";

}
