package com.bit.final_project.exceptions.http;

import org.springframework.http.HttpStatus;

public class EntityExistsException extends BaseException{
    public EntityExistsException(String message) {
        super(message);
    }

    public EntityExistsException(String type, String message) {
        super(type, message);
    }
    @Override
    public HttpStatus getCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
