package com.eCommerceInterface.controller;

import com.eCommerceInterface.dto.request.ProductsRequest;
import com.eCommerceInterface.dto.request.ShopperProductListRequest;
import com.eCommerceInterface.service.InternalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/internal")
public class InternalController {

    @Autowired
    InternalService internalService;
    private static final Logger logger = LoggerFactory.getLogger(InternalController.class);

    @PostMapping("/product-metadata")
    public ResponseEntity<String> saveProduct(@RequestBody ProductsRequest productsRequest){
        try{
            logger.info("Triggered Save product metadata for payload : {}",productsRequest);
            internalService.saveProduct(productsRequest);
            return ResponseEntity.ok().body("Success");
        }catch (Exception exception){
            return ResponseEntity.internalServerError().body("Exception : "+exception.getMessage());
        }
    }

    @PostMapping("/shopper-product-list")
    public ResponseEntity<String> saveShopperProductList(@RequestBody @Valid ShopperProductListRequest shopperProductListRequest){
        try{
            logger.info("Triggered saveShopperProductList for payload : {}",shopperProductListRequest);
            internalService.saveShopperProductList(shopperProductListRequest);
            return ResponseEntity.ok().body("Success");
        }catch (Exception exception){
            return ResponseEntity.internalServerError().body("Exception : "+ exception.getMessage());
        }
    }

}
