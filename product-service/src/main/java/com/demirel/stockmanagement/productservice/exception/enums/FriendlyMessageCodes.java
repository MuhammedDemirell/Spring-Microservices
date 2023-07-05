package com.demirel.stockmanagement.productservice.exception.enums;

public enum FriendlyMessageCodes implements IFriendlyMessageCode{

    OK(1000),
    ERROR(1001),
    SUCCESS(1002),
    PRODUCT_NOT_CREATED_EXCEPTION(1500),
    PRODUCT_SUCCESSFULLY_CREATED(1501),
    PRODUCT_NOT_FOUND_EXCEPTION(1502),
    PRODUCT_SUCCESSFULLY_UPDATED(1503),
    PRODUCT_NOT_UPDATED_EXCEPTION(1504),
    PRODUCT_ALREADY_DELETED_EXCEPTION(1505),
    PRODUCT_SUCCESSFULLY_DELETED(1506);


    private final int value;

    FriendlyMessageCodes(int value) {
        this.value = value;
    }

    @Override
    public int getFriendlyMessageCode() {
        return value;
    }
}
