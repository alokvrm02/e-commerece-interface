package com.eCommerceInterface.service;

import com.eCommerceInterface.dto.Product;
import com.eCommerceInterface.dto.ProductRelevance;
import com.eCommerceInterface.dto.request.ProductsRequest;
import com.eCommerceInterface.dto.request.ShopperProductListRequest;
import com.eCommerceInterface.repository.ProductRelevancyRepository;
import com.eCommerceInterface.repository.ProductRepository;
import com.eCommerceInterface.repository.ShopperProductMappingRepository;
import com.eCommerceInterface.repository.model.ProductRelevancy;
import com.eCommerceInterface.repository.model.Products;
import com.eCommerceInterface.repository.model.ShopperProductMapping;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class InternalService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShopperProductMappingRepository shopperProductMappingRepository;
    @Autowired
    ProductRelevancyRepository productRelevancyRepository;

    private static final Logger logger = LoggerFactory.getLogger(InternalService.class);
    private final ModelMapper modelMapper;

    @Autowired
    public InternalService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public void saveProduct(ProductsRequest productsRequest) throws Exception{
        try{
            List<Product> products = productsRequest.getProducts();
            products.forEach(product -> productRepository.save(modelMapper.map(product,Products.class)));
        }catch (DataIntegrityViolationException dataIntegrityViolationException){
            logger.error("Error occurred while saving Data : {}",dataIntegrityViolationException.getMessage());
            throw new RuntimeException("Failed to save product. Product with same id already exist.");
        }
    }

    public void saveShopperProductList(ShopperProductListRequest shopperProductListRequest) throws Exception,RuntimeException{
         List<ProductRelevance> shelf = shopperProductListRequest.getShelf();
         shelf.forEach(relevance->{
             try{
                 saveShopperProduct(shopperProductListRequest.getShopperId(),relevance);
             }catch (DataIntegrityViolationException dataIntegrityViolationException){
                 logger.error("DataIntegrityViolationException occured while saveShopperProduct : {}",dataIntegrityViolationException.getMessage());
                 throw new RuntimeException("Not able to store data. Data already exist with same id.");
             }catch (Exception exception){
                 logger.error("Exception occurred while saveShopperProduct : {}",exception.getMessage());
                 throw new RuntimeException("Not able to store data. Some error occurred.");
             }
         });
    }

    @Transactional
    public void saveShopperProduct(String shopperId, ProductRelevance productRelevance) throws Exception{
        ProductRelevancy productRelevancy = productRelevancyRepository.findByProductId(productRelevance.getProductId());
        Products product = productRepository.findByProductId(productRelevance.getProductId());
        if(product == null){
            throw new RuntimeException("Product meta data not found for id : "+productRelevance.getProductId());
        }
        if(productRelevancy == null){
            productRelevancy = productRelevancyRepository.save(ProductRelevancy.from(productRelevance.getProductId(),productRelevance.getRelevancyScore(),product));
        }
        ShopperProductMapping shopperProductMapping = shopperProductMappingRepository.findByShopperIdAndProductRelevancyId(shopperId,productRelevancy);
        if(shopperProductMapping == null){
            shopperProductMappingRepository.save(ShopperProductMapping.from(shopperId, productRelevancy));
        }
        productRelevancyRepository.updateRelevanceScoreByProductId(productRelevance.getProductId(),productRelevance.getRelevancyScore());

    }

}
