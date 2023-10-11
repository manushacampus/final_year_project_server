package com.bit.final_project.exceptions.http;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {


    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String type, String message) {
        super(type, message);
    }

    public HttpStatus getCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
