package com.demirel.stockmanagement.productservice.exception.handler;

import com.demirel.stockmanagement.productservice.exception.enums.FriendlyMessageCodes;
import com.demirel.stockmanagement.productservice.exception.exceptions.ProductNotCreatedException;
import com.demirel.stockmanagement.productservice.exception.utils.FriendlyMessageUtils;
import com.demirel.stockmanagement.productservice.model.response.FriendlyMessage;
import com.demirel.stockmanagement.productservice.model.response.InternalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotCreatedException.class)
    public InternalApiResponse<String> handleProductNotCreatedException(ProductNotCreatedException exception){
        return InternalApiResponse.<String>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), FriendlyMessageCodes.ERROR))
                        .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), exception.getFriendlyMessageCode()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessages(Collections.singletonList(exception.getMessage()))
                .build();
    }



}
