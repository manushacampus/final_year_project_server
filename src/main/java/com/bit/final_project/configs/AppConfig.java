package com.bit.final_project.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AppConfig {
    public String environment;
    @Value("${app.environment}")
    public void setEnvironment(String environment){
        this.environment=environment;
        log.info("Environment={}",environment);
    }

    public static String base_url;
    @Value("${app.base_url}")
    public void setBaseURL(String base_url){
        this.base_url=base_url;
        log.info("BaseUrl={}",base_url);
    }
}
