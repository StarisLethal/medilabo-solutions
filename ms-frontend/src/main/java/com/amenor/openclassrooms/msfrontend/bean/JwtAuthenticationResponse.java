package com.amenor.openclassrooms.msfrontend.bean;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    @Getter
    @Setter
    private boolean valid;

}

