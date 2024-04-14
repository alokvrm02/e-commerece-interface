package com.eCommerceInterface.service;

import com.eCommerceInterface.dto.response.ShopperProductResponse;
import com.eCommerceInterface.repository.ShopperProductMappingRepository;
import com.eCommerceInterface.repository.model.ProductRelevancy;
import com.eCommerceInterface.repository.model.Products;
import com.eCommerceInterface.repository.model.ShopperProductMapping;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExternalService {
    @Autowired
    ShopperProductMappingRepository shopperProductMappingRepository;

    @Autowired
    private Cache<String, List<ShopperProductMapping>> shopperCache;

    public List<ShopperProductResponse> getShopperRecords(String shopperId, String category, String brand){
        // Implemented cache when getting data without any filter
        if(category.isEmpty() && brand.isEmpty()){
            List<ShopperProductMapping> shopperProductMappings = shopperCache.get(shopperId,key->this.findByShopperId(shopperId, category, brand));
            if(shopperProductMappings != null){
                return shopperProductMappings.stream().map(ShopperProductResponse::from).collect(Collectors.toList());
            }
        }
        List<ShopperProductMapping> shopperProductMappings = findByShopperId(shopperId, category, brand);
        return shopperProductMappings.stream().map(ShopperProductResponse::from).collect(Collectors.toList());
    }
    public List<ShopperProductMapping> findByShopperId(String shopperId, String category, String brand) {
        return shopperProductMappingRepository.findAll((root, query, criteriaBuilder) -> {
            // Join with ProductRelevancy
            Join<ShopperProductMapping, ProductRelevancy> productRelevancyJoin = root.join("productRelevancyId");

            Join<ProductRelevancy, Products> productJoin = productRelevancyJoin.join("productsId");
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("shopperId"), shopperId));
            if (!category.isEmpty()) {
                predicates.add(criteriaBuilder.equal(productJoin.get("category"), category));
            }
            if (!brand.isEmpty()) {
                predicates.add(criteriaBuilder.equal(productJoin.get("brand"), brand));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
