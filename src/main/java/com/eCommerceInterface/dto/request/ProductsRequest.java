package com.eCommerceInterface.dto.request;

import com.eCommerceInterface.dto.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
public class ProductsRequest {
    @NotEmpty(message = "Products list cannot be empty.")
    List<Product> products;
}
