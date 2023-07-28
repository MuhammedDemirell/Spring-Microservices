package com.demirel.stockmanagement.productservice.controller;

import com.demirel.stockmanagement.productservice.enums.Language;
import com.demirel.stockmanagement.productservice.exception.enums.FriendlyMessageCodes;
import com.demirel.stockmanagement.productservice.exception.utils.FriendlyMessageUtils;
import com.demirel.stockmanagement.productservice.model.entity.Product;
import com.demirel.stockmanagement.productservice.model.request.ProductCreateRequest;
import com.demirel.stockmanagement.productservice.model.request.ProductUpdateRequest;
import com.demirel.stockmanagement.productservice.model.response.FriendlyMessage;
import com.demirel.stockmanagement.productservice.model.response.InternalApiResponse;
import com.demirel.stockmanagement.productservice.model.response.ProductResponse;
import com.demirel.stockmanagement.productservice.service.IProductRepositoryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/1.0/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductRepositoryService productRepositoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{language}/create")
    public InternalApiResponse<ProductResponse> createProduct(@PathVariable("language") Language language,
                                                              @RequestBody ProductCreateRequest productCreateRequest) {
        log.debug("[{}][createProduct] -> request: {}", this.getClass().getSimpleName(), productCreateRequest);
        Product product = productRepositoryService.createProduct(language, productCreateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][createProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_CREATED))
                        .build())
                .httpStatus(HttpStatus.CREATED)
                .hasError(false)
                .payload(productResponse)
                .build();

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{language}/get/{productId}")
    public InternalApiResponse<ProductResponse> getProduct(@PathVariable("language") Language language,
                                                           @PathVariable("productId") Long productId) {
        log.debug("[{}][getProduct] -> request productId: {}", this.getClass().getSimpleName(), productId);
        Product product = productRepositoryService.getProduct(language, productId);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][getProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{language}/update/{productId}")
    public InternalApiResponse<ProductResponse> updateProduct(@PathVariable("language") Language language,
                                                              @PathVariable("productId") Long productId,
                                                              @RequestBody ProductUpdateRequest productUpdateRequest) {
        log.debug("[{}][updateProduct] -> request: {} {}", this.getClass().getSimpleName(), productId, productUpdateRequest);
        Product product = productRepositoryService.updateProduct(language, productId, productUpdateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][updateProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_UPDATED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    @ApiOperation(value = "This endpoint get all product.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{language}/products")
    public InternalApiResponse<List<ProductResponse>> getProducts(@PathVariable("language") Language language) {
        log.debug("[{}][getProducts]", this.getClass().getSimpleName());
        List<Product> products = productRepositoryService.getProducts(language);
        List<ProductResponse> productResponses = convertProductResponseList(products);
        log.debug("[{}][getProducts] -> Response: {}", this.getClass().getSimpleName(), productResponses);
        return InternalApiResponse.<List<ProductResponse>>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponses)
                .build();
    }

    private List<ProductResponse> convertProductResponseList(List<Product> productList) {
        return productList.stream()
                .map(productToConvertResponse -> ProductResponse.builder()
                        .productId(productToConvertResponse.getProductId())
                        .productName(productToConvertResponse.getProductName())
                        .quantity(productToConvertResponse.getQuantity())
                        .price(productToConvertResponse.getPrice())
                        .productCreatedDate(productToConvertResponse.getProductCreatedDate().getTime())
                        .productUpdatedDate(productToConvertResponse.getProductUpdatedDate().getTime())
                        .build())
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{language}/delete/{productId}")
    public InternalApiResponse<ProductResponse> deleteProduct(@PathVariable("language") Language language,
                                                              @PathVariable("productId") Long productId) {
        log.debug("[{}][deleteProduct] -> request productId: {}", this.getClass().getSimpleName(), productId);
        Product product = productRepositoryService.deleteProduct(language, productId);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}][deleteProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCESS))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESSFULLY_DELETED))
                        .build())
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    private ProductResponse convertProductResponse(Product product) {
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
