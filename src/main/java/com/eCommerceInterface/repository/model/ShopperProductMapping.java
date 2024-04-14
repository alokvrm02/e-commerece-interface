package com.eCommerceInterface.repository.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@SuperBuilder
@Table(name = "shopper_product_mapping")
public class ShopperProductMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "shopper_id",nullable = false)
    private String shopperId;
    @ManyToOne
    @JoinColumn(name = "product_relevancy_id")
    private ProductRelevancy productRelevancyId;

    public static ShopperProductMapping from(String shopperId, ProductRelevancy productRelevancy){
        return ShopperProductMapping.builder()
                .shopperId(shopperId)
                .productRelevancyId(productRelevancy).build();
    }
}
