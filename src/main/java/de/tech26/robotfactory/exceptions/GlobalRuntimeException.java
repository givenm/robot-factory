/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tech26.robotfactory.exceptions;

import de.tech26.robotfactory.enums.ErrorCodesEnum;

/**
 *
 * @author givenn
 */
public class GlobalRuntimeException extends RuntimeException {

    private String responseCode;
    private String responseMessage;
    private Integer httpStatusCode;
    private ErrorCodesEnum errEnum;

    /**
     * Helps to customize the message. Everything else should come from enum.
     *
     * @param errEnum - required
     * @param responseMessage - required
     */
    public GlobalRuntimeException(ErrorCodesEnum errEnum, String responseMessage) {
        this.responseCode = errEnum.getResponseCode();
        this.responseMessage = responseMessage;
        this.httpStatusCode = errEnum.getHttpStatusCode();
        this.errEnum = errEnum;
    }

    /**
     * Simple constructor that creates responses based on enum data.
     *
     * @param errEnum - required
     */
    public GlobalRuntimeException(ErrorCodesEnum errEnum) {
        this.responseCode = errEnum.getResponseCode();
        this.responseMessage = errEnum.getResponseMessage();
        this.httpStatusCode = errEnum.getHttpStatusCode();
        this.errEnum = errEnum;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public ErrorCodesEnum getErrEnum() {
        return errEnum;
    }

    public void setErrEnum(ErrorCodesEnum errEnum) {
        this.errEnum = errEnum;
    }
}
