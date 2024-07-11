package com.bit.final_project.configs;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Configuration
@Data
public class EmailConfig {
    public String host;

    @Value("${spring.mail.host}")
    public void setHost(String host) {

        this.host = host;
        log.info("host={}",host);
    }
    public String port;
    @Value("${spring.mail.port}")
    public void setPort(String port) {
        this.port = port;
        log.info("port={}",port);
    }

    public  String userName;
    @Value("${spring.mail.username}")
    public void setUserName(String userName) {
        this.userName = userName;
        log.info("UserName={}",userName);
    }

    public String password;
    @Value("${spring.mail.password}")
    public void setPassword(String password) {
        this.password = password;
        log.info("password={}",password);
    }
}
