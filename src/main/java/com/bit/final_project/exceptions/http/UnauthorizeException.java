package com.bit.final_project.exceptions.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
@Slf4j
public class UnauthorizeException extends BaseException {
    public UnauthorizeException(String message) {
        super(message);
    }

    public UnauthorizeException(String type, String message) {
        super(type, message);
    }

    @Override
    public HttpStatus getCode() {
        log.info("status code unautho={}",HttpStatus.UNAUTHORIZED);
        return HttpStatus.UNAUTHORIZED;
    }
}
