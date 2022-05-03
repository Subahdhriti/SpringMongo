package com.springmongo.wrappergetOffers.service;


import com.springmongo.wrappergetOffers.feign.MsdSearchApi;
import com.springmongo.wrappergetOffers.model.msdmodel.MsdReqModel;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

public interface AsyncFeignCalls {

    public CompletableFuture<ResponseEntity<String>> searchApiCall(String programId, MsdReqModel msdModel);

}