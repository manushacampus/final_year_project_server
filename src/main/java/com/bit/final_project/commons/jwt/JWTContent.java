package com.bit.final_project.commons.jwt;

import lombok.*;

import java.util.HashMap;
import java.util.Map;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTContent {
    private String subject;
    private Map<String, String> payload = new HashMap<>();
    private int expiredIn;
}
