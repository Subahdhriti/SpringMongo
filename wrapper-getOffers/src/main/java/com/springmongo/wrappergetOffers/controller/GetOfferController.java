package com.springmongo.wrappergetOffers.controller;


import com.springmongo.wrappergetOffers.model.RequestModel;
import com.springmongo.wrappergetOffers.service.GetOfferService;
import com.tatadigital.utils.annotations.LogAround;
import com.tatadigital.utils.config.CommonMethods;
import com.tatadigital.utils.exception.model.DigitalInternalServerError;
import com.tatadigital.utils.exception.model.DigitalServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RefreshScope
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/v1")
public class GetOfferController {

    @Autowired
    private GetOfferService getOfferService;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @LogAround
    @PostMapping(path = "/offers", produces = "application/json")
    public ResponseEntity<String> getOffers(@RequestHeader(value = "Program-Id", required = false) String programId, @RequestBody RequestModel req)
    throws DigitalInternalServerError, DigitalServiceException {

        try{
            if(req != null && req.getCustomerHash() != null){
                CommonMethods.addCustomerHashForLogging(req.getCustomerHash());
            }else{
                CommonMethods.addCustomerHashForLogging("Anonymous user..... No CHash found");
            }

            logger.info("Controller method starting....");
            ResponseEntity<String> res = getOfferService.searchEntities(programId, req);
            logger.info("After controller call....");

            return res;

        }catch (DigitalServiceException | DigitalInternalServerError ex){
            throw ex;
        }catch (Exception e){
            throw new DigitalInternalServerError("PAY-110101", e.getMessage(), e);
        }

    }

}
