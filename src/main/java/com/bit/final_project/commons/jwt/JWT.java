package com.bit.final_project.commons.jwt;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Payload;
import com.bit.final_project.commons.DateHelper;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JWT {
    private static final Logger logger = LoggerFactory.getLogger(JWT.class);

    public static String encode(JWTContent jwtContent, String secretKey) {
        JWTCreator.Builder builder = com.auth0.jwt.JWT.create();
        if (jwtContent.getPayload() != null) {
            for (Map.Entry<String, String> entry : jwtContent.getPayload().entrySet()) {
                builder = builder.withClaim(entry.getKey(), entry.getValue());
            }
        }
        String token = builder.withSubject(jwtContent.getSubject())
                .withExpiresAt(DateUtils.addMilliseconds(DateHelper.nowAsDate(), jwtContent.getExpiredIn()))
                .sign(Algorithm.HMAC256(secretKey));
        return token;
    }

    public static JWTContent decode(String token, String secretKey) {
        try {
            JWTContent jwtContent = new JWTContent();
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm).build();
            DecodedJWT content = verifier.verify(token);
            jwtContent.setSubject(content.getSubject());
            Map<String, String> claims = new HashMap<>();
            for (Map.Entry<String, Claim> entry : content.getClaims().entrySet()) {
                claims.put(entry.getKey(), entry.getValue().asString());
            }
            jwtContent.setPayload(claims);
            return jwtContent;
        } catch (JWTVerificationException exp) {
            logger.error("cannot decode jwt token, error = {}", exp.getMessage());
            throw exp;
        }
    }

    public static JWTContent decodeWithoutSecret(String token) {
        try {
            JWTContent jwtContent = JWTContent.builder().build();
            Payload payload = com.auth0.jwt.JWT.decode(token);
            jwtContent.setSubject(payload.getSubject());
            Map<String, String> claims = new HashMap<>();
            for (Map.Entry<String, Claim> entry : payload.getClaims().entrySet()) {
                claims.put(entry.getKey(), entry.getValue().asString());
            }
            jwtContent.setPayload(claims);
            return jwtContent;
        } catch (JWTVerificationException exp) {
            logger.error("cannot decode jwt token, error = {}", exp.getMessage());
            return JWTContent.builder().build();
        }
    }
}
