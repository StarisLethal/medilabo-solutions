package com.amenor.openclassrooms.msgateway.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenValidationRequest {
    private String token;

    public TokenValidationRequest(String token) {
    }
}
