package com.bit.final_project.configs;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Configuration
@Getter
@Setter
public class StorageConfigs {
    public static String folder_path;
    @Value("${app.folder_path}")
    public void setFolder_path(String folder_path){
        this.folder_path=folder_path;
        log.info("folder_path={}",folder_path);
    }

    public String storageType;
    @Value("${app.storage_type}")
    public void setStorageType(String storageType){
        log.info("storageType={}",storageType);
        this.storageType=storageType;
    }

    public static String STORAGE_TYPE="local";
}
