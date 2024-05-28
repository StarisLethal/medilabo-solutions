package com.amenor.openclassrooms.msauthserv.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRequest {
    @NotNull(message = "email:Null") @NotBlank(message = "email:Blank")
    private String email;
    @NotNull(message = "password:Null") @NotBlank(message = "password:Blank")
    private String password;
    private boolean rememberMe;
}
