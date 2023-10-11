package com.bit.final_project.exceptions;

import com.bit.final_project.commons.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class CustomException extends IllegalArgumentException{
    public final static  String INVALID_TOKEN = "INVALID_TOKEN";
    public final static  String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";

    private String message;
    private String type;
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public  CustomException(String type,String message){
        super(message);
        this.message = message;
        this.type =type;
    }

    public String getJsonAsString(){
        JsonNode json = new ObjectMapper().createObjectNode();
        ((ObjectNode) json).put("message",this.message);
        ((ObjectNode) json).put("type",this.type);
        return JSON.objectToString(json);
    }
    public ResponseEntity<Object> getJsonAsResponse(){
        return ResponseEntity.status(this.getStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.getJsonAsString());
    }
}
