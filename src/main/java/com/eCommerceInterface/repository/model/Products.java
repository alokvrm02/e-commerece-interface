package com.eCommerceInterface.repository.model;

import com.eCommerceInterface.dto.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "product_id",nullable = false,unique = true)
    private String productId;

    @Column(name = "category", length = 65535, columnDefinition = "longtext")
    private String category;

    @Column(name = "brand", length = 65535, columnDefinition = "longtext")
    private String brand;

    public static Products from(Product product){
        return Products.builder()
                .productId(product.getProductId())
                .category(product.getCategory())
                .brand(product.getBrand()).build();
    }
}
