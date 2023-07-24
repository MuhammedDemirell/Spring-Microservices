package com.demirel.stockmanagement.productservice.controller;

import com.demirel.stockmanagement.productservice.enums.Language;
import com.demirel.stockmanagement.productservice.exception.enums.FriendlyMessageCodes;
import com.demirel.stockmanagement.productservice.exception.utils.FriendlyMessageUtils;
import com.demirel.stockmanagement.productservice.model.entity.Product;
import com.demirel.stockmanagement.productservice.model.request.ProductCreateRequest;
import com.demirel.stockmanagement.productservice.model.response.FriendlyMessage;
import com.demirel.stockmanagement.productservice.model.response.InternalApiResponse;
import com.demirel.stockmanagement.productservice.model.response.ProductResponse;
import com.demirel.stockmanagement.productservice.service.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/1.0/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductRepositoryService productRepositoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{language}/create")
    public InternalApiResponse<ProductResponse> createProduct(@PathVariable("language")Language language,
                                                              @RequestBody ProductCreateRequest productCreateRequest){
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), productCreateRequest);
        Product product = productRepositoryService.createProduct(language, productCreateRequest);
        ProductResponse productResponse= convertProductResponse(product);
        log.debug("[{}][createProduct] -> response : {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language,FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_CREATED))
                        .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(productResponse)
                .build();
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{language}/get/{productId}")
    public InternalApiResponse<ProductResponse> getProduct(@PathVariable("language")Language language,
                                                           @PathVariable("productId")Long productId){
        log.debug("[{}][getProduct] -> productId: {}", this.getClass().getSimpleName(), productId);
        Product product = productRepositoryService.getProduct(language, productId);
        ProductResponse productResponse= convertProductResponse(product);
        log.debug("[{}][getProduct] -> response : {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language,FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_CREATED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }


    private static ProductResponse convertProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .productCreatedDate(product.getProductCreatedDate().getTime())
                .productUpdatedDate(product.getProductUpdatedDate().getTime())
                .build();
    }
}
