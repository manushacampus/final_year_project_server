package com.bit.final_project.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Slf4j
public class JwtConfig {
    public static int jwtExp;
    @Value("${jwt.expire_in}")
    public  void  setJwtExp(int jwtExp){
        log.info("configuration property = {} = {}","jwt.exp",jwtExp);
        this.jwtExp = jwtExp;
    }
    public static String jwtKey;
    @Value("${jwt.secret_key}")
    public void setJwtKey(String jwtKey){
        log.info("configuration property = {} = {}","jwt.key",jwtKey);
        this.jwtKey = jwtKey;
    }
}
