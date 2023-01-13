package de.tech26.robotfactory.utils;

import de.tech26.robotfactory.dto.responses.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotNull;

public class RestUtil {

    public static ResponseEntity<ApiErrorResponse> toErrorResponseEntity(@NotNull ApiErrorResponse apiErrorResponse) {
        return ResponseEntity
                .status(HttpStatus.valueOf(apiErrorResponse.getResponse().getHttpStatus()))
                .body(apiErrorResponse);
    }

    public static <T> ResponseEntity<T> toResponseEntity(@NotNull  T responseBody, @NotNull HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(responseBody);
    }
}
