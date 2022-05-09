package com.springmongo.funcgetOffers.feign;

import com.springmongo.funcgetOffers.model.msdmodel.MsdReqModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient("TDL-INTEGRATION-MSD-WRAPPER-SEARCHAPI-MS")
public interface MsdSearchApi {

    @PostMapping("/msd-wrapper-searchapi/v1/search")
    public ResponseEntity<String> searchApiCall(@RequestHeader(value="Program-Id",required=false) String programId, @RequestBody MsdReqModel msdReqModel);
}
