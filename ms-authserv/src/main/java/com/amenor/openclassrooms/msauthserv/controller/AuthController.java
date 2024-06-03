package com.amenor.openclassrooms.msauthserv.controller;

import com.amenor.openclassrooms.msauthserv.model.AuthRequest;
import com.amenor.openclassrooms.msauthserv.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api-auth/v1/auth")
public class AuthController {

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        logger.info("Login request: " + authRequest);
        return ResponseEntity.status(201).body(authService.login(authRequest));
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<?> validate(@PathVariable String token) {
        logger.info("Validate request: " + token);
        return ResponseEntity.status(200).body(authService.validate(token));
    }
}
