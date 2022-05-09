package com.springmongo.funcgetOffers.model;

import com.springmongo.funcgetOffers.model.msdmodel.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class RequestModel {

    public String customerHash = "";


    public String query = "";


    public List<Filter> filters = null;

    public List<Filter> additionalFilters = null;
    public Integer page = 0;
    public Integer limit = 0;

    public boolean auto_suggest;

    public boolean onlyFacets;
    public boolean onlyOffers;

    private List<String> facets;
    private Integer facets_limit;
    private Integer facets_page;
    private OrderBy order_by;
    private String slpCategory;
    private String city_name;
    private String lat_lng="";
    private Rec rec_params;
    private String tsss_id="";

}
