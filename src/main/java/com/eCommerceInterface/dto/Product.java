package com.eCommerceInterface.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@SuperBuilder
public class Product {
    @NotNull(message = "productId cannot be null")
    String productId;
    @NotNull(message = "category cannot be null")
    String category;
    @NotNull(message = "Brand cannot be null")
    String brand;
}
