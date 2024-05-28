package com.amenor.openclassrooms.msauthserv.service;

import com.amenor.openclassrooms.msauthserv.model.AuthRequest;
import com.amenor.openclassrooms.msauthserv.model.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest authRequest);

    boolean validate(String token);
}
