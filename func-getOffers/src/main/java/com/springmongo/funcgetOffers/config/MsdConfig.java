package com.springmongo.funcgetOffers.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Configuration
public class MsdConfig {

    @Value("#{'${app.popularoffer.offersfieldlist:catalog_item_id,offer_image_url,offer_title,offer_description,offer_expiry_date,offer_detail_page_url,offer_subtitle,offer_brand_title,offer_brand_logo,offer_ctaType}'.split(',')}")
    private List<String> offersFields;

    @Value("#{'${app.popularoffer.facetlist:brand_name,sub_brand_name,category_name,sub_category_name,offer_type}'.split(',')}")
    private List<String> facetList;

    @Value("#{'${app.popularoffer.featurefieldlist:feature_name,feature_navigation_url}'.split(',')}")
    private List<String> featureFieldList;

}
