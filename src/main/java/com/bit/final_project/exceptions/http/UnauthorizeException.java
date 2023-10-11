package com.bit.final_project.exceptions.http;

import org.springframework.http.HttpStatus;

public class UnauthorizeException extends BaseException {
    public UnauthorizeException(String message) {
        super(message);
    }

    public UnauthorizeException(String type, String message) {
        super(type, message);
    }

    @Override
    public HttpStatus getCode() {
        return HttpStatus.UNAUTHORIZED;
    }
}
