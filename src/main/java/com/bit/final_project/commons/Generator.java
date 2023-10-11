package com.bit.final_project.commons;

import org.apache.commons.lang3.RandomUtils;

import java.util.UUID;

public class Generator {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static int getRandomNumber() {
        return RandomUtils.nextInt(100, 999999999);
    }

    public static String getRandomNumberAsString() {
        return String.valueOf(RandomUtils.nextInt(100, 999999999));
    }

    public static String getRandomString() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
