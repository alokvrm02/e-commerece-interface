package com.eCommerceInterface.repository.model;

import com.eCommerceInterface.dto.ProductRelevance;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "product_relevancy")
public class ProductRelevancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "product_id",nullable = false,unique = true)
    private String productId;

    @Column(name = "relevance_score")
    private Double relevanceScore;
    @OneToOne
    @JoinColumn(name = "product_metadata_id")
    private Products productsId;

    public static ProductRelevancy from(String productId,Double relevanceScore,Products products){
        return ProductRelevancy.builder()
                .productId(productId)
                .relevanceScore(relevanceScore)
                .productsId(products).build();
    }
}
