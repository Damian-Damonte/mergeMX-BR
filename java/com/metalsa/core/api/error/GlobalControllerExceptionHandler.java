/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.api.error;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 *
 * @author juan.vazquez02
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    
    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse illegalArgumentException(IllegalArgumentException ex) {
        return new ApiErrorResponse(400, ex.getMessage());
    }
    
        
    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse noHandlerFoundException(NoHandlerFoundException ex) {
        return new ApiErrorResponse(404, ex.getMessage());
    }    
    
    @ExceptionHandler(value = { GeneralException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse generalException(GeneralException ex) {
        return new ApiErrorResponse(500, ExceptionUtils.getStackTrace(ex));
    }
    
}
