package com.eCommerceInterface.repository;

import com.eCommerceInterface.repository.model.ProductRelevancy;
import com.eCommerceInterface.repository.model.Products;
import com.eCommerceInterface.repository.model.ShopperProductMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopperProductMappingRepository extends JpaRepository<ShopperProductMapping, Long>, JpaSpecificationExecutor<ShopperProductMapping> {

    ShopperProductMapping findByShopperIdAndProductRelevancyId(String shopperId, ProductRelevancy productRelevancy);
}
