package com.metalsa.error;

import com.metalsa.core.api.error.ApiErrorResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * controlador de excepciones para el servicio rest.
 *
 * @author ruben.bresler
 */
@RestControllerAdvice
@Log4j
public class ExceptionHandlerController  {

    @Qualifier("application")
    @Autowired
    private MessageSource messages;

    @ExceptionHandler(value = { NoOwnerException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorResponse illegalArgumentException(NoOwnerException ex) {
        return new ApiErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = { NotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse notFoundException(NotFoundException ex) {
        return new ApiErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = { RuntimeException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse runtimeException(RuntimeException ex) {
        log.error(ex);
        ex.printStackTrace();
        return new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler(value = { NoSuchMethodException.class, NoSuchMethodError.class })
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ApiErrorResponse invalidMethod(Exception ex) {
        log.debug(ex);
        ex.printStackTrace();
        return new ApiErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(),
                messages.getMessage("invalid.rest.endpoint", null, LocaleContextHolder.getLocale()));
    }
}
