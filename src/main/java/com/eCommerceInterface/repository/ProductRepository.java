package com.eCommerceInterface.repository;

import com.eCommerceInterface.repository.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {

    Products findByProductId(String productID);
}
