package com.demirel.stockmanagement.productservice.exception.enums;

public class FriendlyMessageCode implements IFriendlyMessageCode{


    private final int value;

    FriendlyMessageCode(int value) {
        this.value = value;
    }

    @Override
    public int getFriendlyMessageCode() {
        return value;
    }
}
