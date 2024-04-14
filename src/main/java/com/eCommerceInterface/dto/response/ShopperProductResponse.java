package com.eCommerceInterface.dto.response;

import com.eCommerceInterface.repository.model.ShopperProductMapping;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class ShopperProductResponse {

    String shopperId;
    String productId;
    Double relevanceScore;
    String category;
    String brand;

    public static ShopperProductResponse from(ShopperProductMapping shopperProductMapping){
        return ShopperProductResponse.builder()
                .shopperId(shopperProductMapping.getShopperId())
                .productId(shopperProductMapping.getProductRelevancyId().getProductId())
                .relevanceScore(shopperProductMapping.getProductRelevancyId().getRelevanceScore())
                .category(shopperProductMapping.getProductRelevancyId().getProductsId().getCategory())
                .brand(shopperProductMapping.getProductRelevancyId().getProductsId().getBrand()).build();
    }
}
