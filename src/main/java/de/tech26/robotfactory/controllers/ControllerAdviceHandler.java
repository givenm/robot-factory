/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tech26.robotfactory.controllers;

import de.tech26.robotfactory.dto.responses.ApiErrorResponse;
import de.tech26.robotfactory.enums.ErrorCodesEnum;
import de.tech26.robotfactory.exceptions.GlobalRuntimeException;
import de.tech26.robotfactory.utils.RestUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author givenn
 */
@RestControllerAdvice
public class ControllerAdviceHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(GlobalRuntimeException.class)
    ResponseEntity<?> handleGlobalRuntimeException(HttpServletRequest request, GlobalRuntimeException ex) {
        return RestUtil.toErrorResponseEntity(new ApiErrorResponse(ex.getErrEnum(), Collections.singletonList(ex.getResponseMessage())));
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NotNull HttpHeaders headers, @NotNull HttpStatus status, @NotNull WebRequest request) {
        // Capture API validation errors and format them nicely
        final List<String> errors = new LinkedList<>();
        ex.getBindingResult().getAllErrors()
                .forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.add(String.format("%s: %s", fieldName, errorMessage));
                });
        Object apiErrorResponse = new ApiErrorResponse(ErrorCodesEnum.REQUIRED_FIELD_INVALID, errors);
        return RestUtil.toResponseEntity(apiErrorResponse, HttpStatus.valueOf(ErrorCodesEnum.REQUIRED_FIELD_INVALID.getHttpStatusCode()));
    }
}
