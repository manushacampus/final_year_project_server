package com.bit.final_project.component;

import com.bit.final_project.commons.storage.service.FilesStorageService;
import com.bit.final_project.commons.storage.service.impl.LocalFileStorage;
import com.bit.final_project.configs.GlobalConfigs;
import com.bit.final_project.configs.StorageConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class AppComponent {
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    StorageConfigs storageConfigs;
    @Bean
    public LocalFileStorage storageService() {

//        if(storageConfigs.getStorageType().equals(StorageConfigs.STORAGE_TYPE)){
            GlobalConfigs configs = new GlobalConfigs();
//            configs.setHomeDir(System.getenv("TRAVEL_PASS_FOLDER_PATH"));
        configs.setHomeDir(storageConfigs.getFolder_path());
            var localStorage = new LocalFileStorage(configs);
            localStorage.setup();
            return localStorage;
//        }

    }
}
