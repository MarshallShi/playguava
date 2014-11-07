package com.dianping.customer.report.biz.enums;


/**
 * User: feipeng
 *
 */
public enum ApplicationExceptionEnum {
    SHOP_NOT_FOUND(1),
    CUSTOMER_NOT_FOUND(2),
    OPERATION_FAILED(3),
    CUSTOMER_PRIMARY_BANK_ACCOUNT_NOT_FOUND(4),
    SET_PRIMARY_BANK_ACCOUNT_ERROR(5),
    PREVIEW_DEAL_GROUP_ID_NOT_FOUND(6),
    CUSTOMER_PRIMARY_BANK_ACCOUNT_SHOULD_NOT_NULL(7),
    PLACE_CITY_MUST_NOT_BE_NULL(8),
    SHOP_SHOULD_NOT_NULL_WHEN_SHOP_BANK_ACCOUNT(9),
    CONTRACT_NOT_EXIST(10),
    CONTRACT_NOT_ASSN_CUSTOMER(11),
    CUSTOMER_FOUND_ERROR(12);
    private final int code;

    ApplicationExceptionEnum(int code) {
        this.code = code;
    }
}
