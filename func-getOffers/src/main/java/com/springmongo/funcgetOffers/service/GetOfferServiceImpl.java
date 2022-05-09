package com.springmongo.funcgetOffers.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.springmongo.funcgetOffers.model.RequestModel;
import com.springmongo.funcgetOffers.model.msdmodel.MsdReqModel;
import com.springmongo.funcgetOffers.model.msdmodel.OrderBy;
import com.springmongo.funcgetOffers.model.msdmodel.Rec;
import com.tatadigital.utils.annotations.LogAround;
import com.tatadigital.utils.exception.model.DigitalInternalServerError;
import com.tatadigital.utils.exception.model.DigitalServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GetOfferServiceImpl implements GetOfferService {


    @Value("#{'${app.popularoffer.offersfieldlist:backgroundImageURL,title,offer_expiry_date,ctaURL,description,offer_subtitle,brandLogo,brandName,ctaType,offer_type,ctaLabel,isPromotionSharable,promotionShareType,totalShares}'.split(',')}")
    private List<String> offersFields;

    @Value("#{'${app.popularoffer.facetlist:brand_name,sub_brand_name,category_name,sub_category_name,offer_type}'.split(',')}")
    private List<String> facetList;

    @Value("${app.popularoffer.orderbylist:relevance,trending,newly_added_date,offer_expiry_date}")
    private String orderbylist;


    Logger logger = LoggerFactory.getLogger(this.getClass());



    @Autowired
    AsyncFeignCalls feignCalls;


    @LogAround
    @Override
    public ResponseEntity<String> searchEntities(String programId, RequestModel req) throws DigitalServiceException, DigitalInternalServerError {
        try{
            MsdReqModel msdReqModel = new MsdReqModel();


            msdReqModel.setOrg_user_id(req.getCustomerHash());
            msdReqModel.setCorrelation_id(req.getCustomerHash());
            msdReqModel.setCatalog("tcp_offers");
            msdReqModel.setClient("TCP");
            msdReqModel.setUser_id(req.getCustomerHash());
            Rec rec = new Rec();
            if(req.getSlpCategory() != null){
                rec.setSlp_category(req.getSlpCategory().trim().toLowerCase().replace('&','n'));
            }
            if(req.getRec_params() != null && !req.getRec_params().getLimitedOffers().equalsIgnoreCase("")){
                rec.setLimitedOffers(req.getRec_params().getLimitedOffers());
            }
            if(req.getCity_name() != null){
                rec.setCity_name(req.getCity_name());
            }
            if(req.getLat_lng() != null){
                rec.setLat_lng(req.getLat_lng());
            }
            rec.setTsss_id(req.getTsss_id());
            msdReqModel.setRec_params(rec);

            if(req.getFilters() != null){
                msdReqModel.setFilters(req.getFilters());
            }

            if (req.getAdditionalFilters() != null) {
                if(req.getFilters()!=null) {
                    msdReqModel.getFilters().addAll(req.getAdditionalFilters());
                }
                else
                {
                    msdReqModel.setFilters(req.getAdditionalFilters());
                }
            }

            msdReqModel.setMad_uuid(req.getCustomerHash());
            if (req.getQuery() != null && !req.getQuery().equalsIgnoreCase("")) {
                msdReqModel.setQuery(req.getQuery());
                msdReqModel.setAuto_suggest(req.isAuto_suggest());
                if (req.isAuto_suggest()) {
                    msdReqModel.setAuto_correct(false);
                    msdReqModel.setAuto_populate(false);
                } else {
                    msdReqModel.setAuto_correct(true);
                    msdReqModel.setAuto_populate(true);
                }
            }
            OrderBy order = new OrderBy();
            order.setField(null==req.getOrder_by() || null==req.getOrder_by().getField()?"relevance":req.getOrder_by().getField());
            order.setOrder(null == req.getOrder_by() || null == req.getOrder_by().getOrder() ? "desc" : req.getOrder_by().getOrder());
            order.setValue(null==req.getOrder_by() || null==req.getOrder_by().getValue()?null:req.getOrder_by().getValue());

            List orderlist = new ArrayList();
            orderlist.add(order);
            msdReqModel.setOrder_by(orderlist);

            msdReqModel.setFields(offersFields);
            msdReqModel.setLimit(req.getLimit()==0? 25:req.getLimit());
            msdReqModel.setPage(req.getPage()==0? 1:req.getPage());

            msdReqModel.setFacets_limit(req.getFacets_limit());
            msdReqModel.setFacets_page(req.getFacets_page());
            if(req.getFacets()==null || req.getFacets().size()==0)
                msdReqModel.setFacets(facetList);
            else
                msdReqModel.setFacets(req.getFacets());

            logger.info("Making async MSD search call....");
            CompletableFuture<ResponseEntity<String>> com_res = feignCalls.searchApiCall(programId, msdReqModel);
            logger.info("Async search call done....");
            CompletableFuture.allOf(com_res).join();

            ResponseEntity<String> res = com_res.get();
            if (res == null || res.getStatusCodeValue() != 200) {
                throw new DigitalServiceException("MSD-210102", "Error while fetching Msd search", null);
            }

            JsonObject jObject = JsonParser.parseString(res.getBody()).getAsJsonObject();
            JsonObject jObject2 = jObject.getAsJsonArray("results").get(0).getAsJsonObject();

            return new ResponseEntity<>(jObject.toString(), HttpStatus.OK);
        }catch (DigitalServiceException ex) {
            throw ex;
        }catch (Exception e){
            throw new DigitalInternalServerError("MSD-210101", e.getMessage(), e);
        }
    }
}
