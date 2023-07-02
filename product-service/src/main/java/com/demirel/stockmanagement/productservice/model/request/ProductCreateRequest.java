package com.demirel.stockmanagement.productservice.model.request;

import lombok.Data;

@Data
public class ProductCreateRequest {

    private String productName;

    private int quantity;

    private Double price;


}
