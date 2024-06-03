package com.amenor.openclassrooms.msauthserv.service;

import com.amenor.openclassrooms.msauthserv.exception.AuthException;
import com.amenor.openclassrooms.msauthserv.model.AuthRequest;
import com.amenor.openclassrooms.msauthserv.model.AuthResponse;
import com.amenor.openclassrooms.msauthserv.model.User;
import com.amenor.openclassrooms.msauthserv.repositories.UserRepository;
import com.amenor.openclassrooms.msauthserv.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        Optional<User> user = userRepository.findById(authRequest.getEmail());

        if (user.isEmpty()) {
            throw new AuthException.AuthFailedException("Invalid email or password");
        }

        if (!bCryptPasswordEncoder.matches(authRequest.getPassword(), user.get().getPassword())) {
            throw new AuthException.AuthFailedException("Invalid email or password");
        }
        return AuthResponse.builder().token(jwtUtil.get(authRequest.getEmail())).build();
    }

    @Override
    public boolean validate(String token) {return jwtUtil.verify(token, false);}
}
