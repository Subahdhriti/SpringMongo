package com.springmongo.funcgetOffers.model.msdmodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class MsdReqModel {
    public String correlation_id ;
    public String client ;
    public String catalog;
    public String org_user_id;
    public String mad_uuid;
    public String user_id="";
    public List<String> fields;
    public List<String> facets;
    public String query="";
    public List<OrderBy> order_by;
    public List<Filter> filters;
    public Integer page;
    public Integer limit;
    public String multi_search_field;
    public boolean auto_suggest;
    public boolean auto_correct;
    public boolean auto_populate;
    public Integer facets_limit;
    public Integer facets_page=1;
    private Rec rec_params;
    private String tsss_id="";
}
