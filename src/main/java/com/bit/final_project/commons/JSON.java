package com.bit.final_project.commons;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JSON {
    private static final Logger logger = LoggerFactory.getLogger(JSON.class);

    public static String objectToString(Object object) {
        try {
            return getMapper().writeValueAsString(object);
        } catch (Exception exp) {
            logger.error("object to string error = {}", exp.getMessage());
            exp.printStackTrace();
            return "{}";
        }
    }

    public static <T> T stringToObject(String json, Class<T> type) {

        try {
            return getMapper().readValue(json, type);
        } catch (IOException exp) {
            logger.error("string to object error = {}", exp.getMessage());
            exp.printStackTrace();
            return null;
        }
    }

    public static <T> T stringToObject(String json, TypeReference<T> type) {

        try {
            return getMapper().readValue(json, type);
        } catch (IOException exp) {
            logger.error("string to object error = {}", exp.getMessage());
            exp.printStackTrace();
            return null;
        }
    }

    public static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

}
