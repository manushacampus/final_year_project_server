package com.bit.final_project.component;

import com.bit.final_project.commons.jwt.JWT;
import com.bit.final_project.commons.jwt.JWTContent;
import com.bit.final_project.configs.JwtConfig;
import com.bit.final_project.security.filters.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class JWTAppendAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if(CurrentUser.getUser() == null){
            return body;
        }
        JWTContent content = new JWTContent();
        Map m = new HashMap();
        m.put("userId", CurrentUser.getUser().getId());
        content.setPayload(m);
        content.setExpiredIn(JwtConfig.jwtExp);

        String token = "Bearer " + JWT.encode(content,JwtConfig.jwtKey);
        log.info("Token Is {}",token);
        response.getHeaders().add("Authorization",token);
        return body;
    }
}
