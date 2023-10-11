package com.bit.final_project.exceptions.http;

import org.springframework.http.HttpStatus;

public class InternalErrorException extends BaseException {

    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";

    public InternalErrorException() {
    }

    public InternalErrorException(String message) {
        super(message);
    }

    public InternalErrorException(String type, String message) {
        super(type, message);
    }

    @Override
    public HttpStatus getCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
