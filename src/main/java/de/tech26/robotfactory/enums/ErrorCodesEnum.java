package de.tech26.robotfactory.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCodesEnum {

    REQUIRED_FIELD_INVALID(1, "Required field invalid.", HttpStatus.BAD_REQUEST),
    NOT_ENOUGH_COMPONENTS(2, "Provide valid number of components for assembly.", HttpStatus.BAD_REQUEST),
    ITEM_NOT_FOUND(3, "Item with provided query not found.", HttpStatus.NOT_FOUND),
    PRODUCT_OUT_OF_STOCK(5, "Product is out of stock. Please check again later.", HttpStatus.UNPROCESSABLE_ENTITY),
    MISSING_PRODUCT_PART(6, "An essential component is missing to fulfil the order.", HttpStatus.INTERNAL_SERVER_ERROR),
    UNKNOWN_PRODUCT(7, "Please provide a valid product type.", HttpStatus.BAD_REQUEST),
    GENERAL_SERVER_ERROR(8, "An unexpected error occurred. Please try again.", HttpStatus.BAD_REQUEST);
    private static final String RESPONSE_CODE_FORMAT = "%04d"; //This will pad response code value with zeros up to length 4.
    private final String responseCode;
    private final Integer httpStatusCode;
    private final String responseMessage;
// ---------------------------------------------------------------------------------------------------------

    /**
     * Creates the enum with responseCode and responseDesc.
     */
    ErrorCodesEnum(int responseCode, String responseMessage, HttpStatus httpStatus) {
        if (responseCode >= 0) {
            this.responseCode = String.format(RESPONSE_CODE_FORMAT, responseCode);
        } else {
            this.responseCode = String.valueOf(responseCode);
        }
        this.responseMessage = responseMessage;
        this.httpStatusCode = httpStatus.value();
    }

    public String getResponseCode() {
        return responseCode;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
