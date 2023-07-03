package com.demirel.stockmanagement.productservice.service.impl;

import com.demirel.stockmanagement.productservice.enums.Language;
import com.demirel.stockmanagement.productservice.exception.enums.FriendlyMessageCode;
import com.demirel.stockmanagement.productservice.model.entity.Product;
import com.demirel.stockmanagement.productservice.model.request.ProductCreateRequest;
import com.demirel.stockmanagement.productservice.model.request.ProductUpdateRequest;
import com.demirel.stockmanagement.productservice.repository.ProductRepository;
import com.demirel.stockmanagement.productservice.service.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductRepositoryImpl implements IProductRepositoryService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Language language, ProductCreateRequest productCreateRequest) {

        log.debug("[{}][createProduct] request: {}", this.getClass().getSimpleName(), productCreateRequest);

        try {
            Product product = Product.builder()
                    .productName(productCreateRequest.getProductName())
                    .quantity(productCreateRequest.getQuantity())
                    .price(productCreateRequest.getPrice())
                    .deleted(false)
                    .build();
            Product productResponse = productRepository.save(product);

            log.debug("[{}][createProduct] response: {}", this.getClass().getSimpleName(), productResponse);
            return productResponse;
        } catch (Exception e) {
           throw new ProductNotCreatedException(language, FriendlyMessageCodes.PRODUCT_NOT_CREATED_EXCEPTION,"Product request : " + productCreateRequest.toString());
        }

    }

    @Override
    public Product getProductById(Language language, Long productId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts(Language language) {
        return null;
    }

    @Override
    public Product updateProduct(Language language, Long productId, ProductUpdateRequest productUpdateRequest) {
        return null;
    }

    @Override
    public Product deleteProduct(Language language, Long productId) {
        return null;
    }
}
