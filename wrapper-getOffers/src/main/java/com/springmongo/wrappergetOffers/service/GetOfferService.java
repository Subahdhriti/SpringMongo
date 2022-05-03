package com.springmongo.wrappergetOffers.service;

import com.springmongo.wrappergetOffers.model.RequestModel;
import com.tatadigital.utils.exception.model.DigitalInternalServerError;
import com.tatadigital.utils.exception.model.DigitalServiceException;
import org.springframework.http.ResponseEntity;

public interface GetOfferService {

    ResponseEntity<String> searchEntities(String programId, RequestModel req) throws DigitalServiceException, DigitalInternalServerError;
}
