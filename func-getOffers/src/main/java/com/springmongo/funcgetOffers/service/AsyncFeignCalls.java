package com.springmongo.funcgetOffers.service;


import com.springmongo.funcgetOffers.model.msdmodel.MsdReqModel;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

public interface AsyncFeignCalls {

    public CompletableFuture<ResponseEntity<String>> searchApiCall(String programId, MsdReqModel msdModel);

}