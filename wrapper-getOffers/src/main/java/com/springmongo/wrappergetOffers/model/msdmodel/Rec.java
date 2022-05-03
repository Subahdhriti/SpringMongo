package com.springmongo.wrappergetOffers.model.msdmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rec {

    private String slp_category;
    private String limitedOffers;
    private String city_name="";
    private String lat_lng="";
    private String tsss_id="";

}
