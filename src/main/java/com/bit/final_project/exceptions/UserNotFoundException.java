package com.bit.final_project.exceptions;

import com.bit.final_project.exceptions.http.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String type, String message) {
        super(type, message);
    }
    @Override
    public HttpStatus getCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
