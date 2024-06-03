package com.amenor.openclassrooms.msauthserv.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import com.amenor.openclassrooms.msauthserv.exception.AuthException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${JWT_SECRET}")
    private String secret;

    private JwtUtil() {
    }

    public JwtUtil(String secret) {
        this.secret = secret;
    }

    public String get(String email) {
        try {
            return JWT.create().withSubject(email).withIssuedAt(new Date())
                    .withExpiresAt(Instant.ofEpochSecond(new Date().getTime())).sign(Algorithm.HMAC256(secret));
        } catch (Exception e) {
           throw new AuthException.AuthFailedException("Auth failed");
        }
    }

    public String getEmail(String token, boolean throwException) {
        try {
            return JWT.require(Algorithm.HMAC256(secret)).build().verify(token.replace("Bearer ", "")).getSubject();
        } catch (Exception e) {
            if (throwException) {
                throw new AuthException.AuthFailedException("Auth failed");
            } else {
                return null;
            }
        }
    }

    public boolean verify(String token, boolean throwException) {
        return getEmail(token, throwException) != null;
    }
}
