package com.springmongo.funcgetOffers.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Rec {

    //private String slp_category;
    private String limitedOffers="";
}
