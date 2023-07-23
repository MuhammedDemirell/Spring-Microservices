package com.demirel.stockmanagement.productservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductResponse {
    private String productId;
    private String productName;
    private String quantity;
    private String price;
    private Long productCreatedDate;
    private Long productUpdatedDate;
}
