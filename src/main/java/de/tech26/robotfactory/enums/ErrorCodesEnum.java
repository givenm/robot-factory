package de.tech26.robotfactory.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCodesEnum {

    REQUIRED_FIELD_INVALID(1, "Required field invalid.", HttpStatus.BAD_REQUEST),
    NOT_ENOUGH_COMPONENTS(2, "Provide valid number of components for assembly.", HttpStatus.BAD_REQUEST),
    ITEM_NOT_FOUND(3, "Item with provided query not found.", HttpStatus.NOT_FOUND);
    private static final String RESPONSE_CODE_FORMAT = "%04d"; //This will pad response code value with zeros up to length 4.
    private final String responseCode;
    private final Integer httpStatusCode;
    private final String responseMessage;
// ---------------------------------------------------------------------------------------------------------

    /**
     * Creates the enum with responseCode and responseDesc.
     */
    private ErrorCodesEnum(int responseCode, String responseMessage, HttpStatus httpStatus) {
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
