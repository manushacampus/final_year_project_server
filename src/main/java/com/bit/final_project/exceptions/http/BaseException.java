package com.bit.final_project.exceptions.http;

import com.bit.final_project.commons.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class BaseException extends RuntimeException{
    private String message;
    private String type;
    private Exception rootException;

    public BaseException() {
    }

    public BaseException(Exception rootException, String message, Object... params) {
        super(message);
        this.message = formattedMessage(message, params);
        this.rootException = rootException;
    }


    public BaseException(String message, Object... params) {
        super(message);
        this.message = formattedMessage(message, params);
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(String type, String message) {
        super(message);
        this.type = type;
        this.message = message;
    }
    public HttpStatus getCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public String getJsonAsString() {
        Map<String, String> object = new HashMap<>();
        object.put("message", this.message);
        object.put("type", this.type);
        return JSON.objectToString(object);
    }

    public ResponseEntity<Object> getJsonAsResponse() {
        return ResponseEntity.status(this.getCode().value())
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.getJsonAsString());
    }

    private String formattedMessage(String msg, Object[] params) {
        for (Object param : params) {
            msg = msg.replaceFirst("\\{\\}", param.toString());
        }
        return msg;
    }

    public String getMessage() {
        if(rootException == null) {
            return this.message;
        }
        return this.message + " : root exception = " + rootException.getMessage();
    }
}
