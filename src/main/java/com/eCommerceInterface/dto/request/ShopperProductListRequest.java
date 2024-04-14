package com.eCommerceInterface.dto.request;

import com.eCommerceInterface.dto.ProductRelevance;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
public class ShopperProductListRequest {
    @NotNull(message = "Shopper id cannot be null")
    @Size(min = 2)
    String shopperId;
    @NotEmpty(message = "Shelf cannot be empty")
    List<ProductRelevance> shelf;
}
