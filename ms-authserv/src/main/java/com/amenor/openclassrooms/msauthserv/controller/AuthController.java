package com.amenor.openclassrooms.msauthserv.controller;

import com.amenor.openclassrooms.msauthserv.dto.AuthRequest;
import com.amenor.openclassrooms.msauthserv.model.UserCredential;
import com.amenor.openclassrooms.msauthserv.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential userCredential) {
        return authService.saveUser(userCredential);
    }

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@Valid @RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            String jwt = authService.generateToken(authRequest.getUsername());
            return ResponseEntity.ok(jwt);

        }else {
            throw new BadCredentialsException("Bad credentials");
        }

    }

    @PostMapping("/validate")
    public String validateToken(HttpSession session) {
        String token = (String) session.getAttribute("token");
        authService.validateToken(token);
        return "Valid Token";
    }
}
