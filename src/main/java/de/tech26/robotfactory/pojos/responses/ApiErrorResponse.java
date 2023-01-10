/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tech26.robotfactory.pojos.responses;
import de.tech26.robotfactory.enums.ErrorCodesEnum;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author givenn
 */
public class ApiErrorResponse {

    @NotNull
    private List<String> errorMessages;
    @NotNull
    private ResponseStatus response;

    /**
     * Use this constructor for specifying custom errors to client instead of
     * using ErrorCodesEnum default message.
     *
     * @param errorCodesEnum Required
     * @param errorMessages - List of error messages to send to client
     */
    public ApiErrorResponse(@NotNull ErrorCodesEnum errorCodesEnum, @NotNull @Min(1) List<String> errorMessages) {
            this.response = new ResponseStatus(errorCodesEnum.getResponseCode(), errorCodesEnum.getHttpStatusCode());
            this.errorMessages = errorMessages;
    }

    /**
     * Use this constructor for defaulting to ErrorCodesEnum's message.
     *
     * @param errorCodesEnum - Required. This will default to success = true.
     */
    public ApiErrorResponse(ErrorCodesEnum errorCodesEnum) {
        this.response = new ResponseStatus(errorCodesEnum.getResponseCode(), errorCodesEnum.getHttpStatusCode());
        this.errorMessages = Collections.singletonList(errorCodesEnum.getResponseMessage());
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public ResponseStatus getResponse() {
        return response;
    }

    public void setResponse(ResponseStatus response) {
        this.response = response;
    }
}
