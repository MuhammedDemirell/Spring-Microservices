package com.demirel.stockmanagement.productservice.service;

import com.demirel.stockmanagement.productservice.enums.Language;
import com.demirel.stockmanagement.productservice.model.entity.Product;
import com.demirel.stockmanagement.productservice.model.request.ProductCreateRequest;
import com.demirel.stockmanagement.productservice.model.request.ProductUpdateRequest;

import java.util.List;

public interface IProductRepositoryService {

    Product createProduct(Language language , ProductCreateRequest productCreateRequest);

    Product getProductById(Language language , Long productId);

    List<Product> getAllProducts(Language language);

    Product updateProduct(Language language , Long productId , ProductUpdateRequest productUpdateRequest);

    Product deleteProduct(Language language , Long productId);


}
