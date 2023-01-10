/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tech26.robotfactory.pojos.responses;
import javax.validation.constraints.NotNull;

/**
 *
 * @author givenn
 */
public class ResponseStatus {

    @NotNull
    private String code;
    @NotNull
    private Integer httpStatus;

    public ResponseStatus(String code, Integer httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }
}
