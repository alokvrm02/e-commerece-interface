package com.eCommerceInterface.controller;

import com.eCommerceInterface.dto.response.ShopperProductResponse;
import com.eCommerceInterface.exception.customExceptions.DataNotFoundException;
import com.eCommerceInterface.repository.model.ShopperProductMapping;
import com.eCommerceInterface.service.ExternalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
                                                                            @RequestParam (required = false, defaultValue = "") String brand,
                                                                             @RequestParam (required = false) Sort.Direction sortingDirection,
                                                                             @RequestHeader int pageNumber,
                                                                             @RequestHeader int pageSize) throws Exception, DataNotFoundException {

            List<ShopperProductResponse> shopperProductResponses = externalService.getShopperRecords(shopperId,category,brand,pageNumber,pageSize,sortingDirection);
            return ResponseEntity.ok().body(shopperProductResponses);

    }

}
