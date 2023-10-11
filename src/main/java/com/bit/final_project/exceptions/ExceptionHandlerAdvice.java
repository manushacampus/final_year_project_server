package com.bit.final_project.exceptions;


import com.bit.final_project.exceptions.http.BadRequestException;
import com.bit.final_project.exceptions.http.InternalErrorException;
import com.bit.final_project.exceptions.http.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { BaseException.class})
    protected ResponseEntity<Object> handleCustomException(BaseException exception, HttpServletRequest request) {
        log.error("exception occurred [BaseException] type = {} error = {}",exception.getClass().getCanonicalName(), exception.getMessage());
        exception.printStackTrace();
        return exception.getJsonAsResponse();
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception exception) {
        log.error("exception occurred [Exception] error = {}", exception.getMessage());
        exception.printStackTrace();
        var internalExp = new InternalErrorException(InternalErrorException.INTERNAL_SERVER_ERROR, exception.getMessage());
        return internalExp.getJsonAsResponse();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("exception occurred [Malformed exception] error = {}", ex.getMessage());
        log.info("http response url = {}, response = {}",  request.getContextPath(), status.value());
        ex.printStackTrace();
        var exception = new BadRequestException("malformed request body. Please check for correct date/time formats and data types");
        return exception.getJsonAsResponse();
    }


}
