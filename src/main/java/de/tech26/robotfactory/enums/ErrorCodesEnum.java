package de.tech26.robotfactory.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCodesEnum {

    REQUIRED_FIELD_INVALID(1, "Required field invalid.", HttpStatus.BAD_REQUEST);
    private static final String RESPONSE_CODE_FORMAT = "P%04d"; //This will pad response code value with zeros up to length 4.
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
