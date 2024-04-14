package com.eCommerceInterface.repository;

import com.eCommerceInterface.repository.model.ProductRelevancy;
import com.eCommerceInterface.repository.model.ShopperProductMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRelevancyRepository extends JpaRepository<ProductRelevancy, Long>, JpaSpecificationExecutor<ProductRelevancy> {
    ProductRelevancy findByProductId(String productId);

    @Transactional
    @Modifying
    @Query("UPDATE ProductRelevancy pr SET pr.relevanceScore = :relevanceScore WHERE pr.productId = :productId")
    public void updateRelevanceScoreByProductId(@Param("productId") String productId, @Param("relevanceScore") Double relevanceScore);
}
