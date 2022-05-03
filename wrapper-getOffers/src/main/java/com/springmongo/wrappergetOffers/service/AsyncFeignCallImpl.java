package com.springmongo.wrappergetOffers.service;

import com.springmongo.wrappergetOffers.feign.MsdSearchApi;
import com.springmongo.wrappergetOffers.model.msdmodel.MsdReqModel;
import com.springmongo.wrappergetOffers.service.AsyncFeignCalls;
import com.tatadigital.utils.annotations.LogAround;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncFeignCallImpl implements AsyncFeignCalls {

    @Autowired
    MsdSearchApi msdSearchApi;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @LogAround
    @Override
    public CompletableFuture<ResponseEntity<String>> searchApiCall(String programId, MsdReqModel msdModel) {


        ResponseEntity<String> msdRes = msdSearchApi.searchApiCall(programId, msdModel);

        logger.info("Returning MSD search call.....");

        return CompletableFuture.completedFuture(new ResponseEntity<String>(msdRes.getBody(), HttpStatus.OK));
    }

}