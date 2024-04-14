package com.eCommerceInterface.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class ProductRelevance {
    String productId;
    Double relevancyScore;
}
