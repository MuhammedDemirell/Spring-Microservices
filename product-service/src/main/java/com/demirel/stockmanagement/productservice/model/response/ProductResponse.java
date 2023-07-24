package com.demirel.stockmanagement.productservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductResponse {
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;
    private Long productCreatedDate;
    private Long productUpdatedDate;
}
