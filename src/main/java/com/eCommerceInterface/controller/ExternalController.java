package com.eCommerceInterface.controller;

import com.eCommerceInterface.dto.response.ShopperProductResponse;
import com.eCommerceInterface.repository.model.ShopperProductMapping;
import com.eCommerceInterface.service.ExternalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/external")
public class ExternalController {
    @Autowired
    ExternalService externalService;
    private static final Logger logger = LoggerFactory.getLogger(ExternalController.class);

    @GetMapping("/shopper/{shopperId}")
    public ResponseEntity<List<ShopperProductResponse>> getProductsByShopper(@PathVariable String shopperId,
                                                                            @RequestParam (required = false, defaultValue = "") String category,
                                                                            @RequestParam (required = false, defaultValue = "") String brand){
        try {
            List<ShopperProductResponse> shopperProductResponses = externalService.getShopperRecords(shopperId,category,brand);
            return ResponseEntity.ok().body(shopperProductResponses);
        }catch (Exception e){
            logger.error("Error Occured : {}",e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
