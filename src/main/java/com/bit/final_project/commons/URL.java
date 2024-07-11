package com.bit.final_project.commons;

import com.bit.final_project.configs.AppConfig;

public class URL {
    public static String fileStorageUrl= AppConfig.base_url+"/api/file/{type}/{fileName}";
//    public static String forgetPasswordLink=AppConfigs.base_url+"/api/forget-password/verify/{code}?";
}
