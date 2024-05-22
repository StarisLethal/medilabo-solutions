package com.amenor.openclassrooms.msfrontend.service;

import com.amenor.openclassrooms.msfrontend.model.User;
import com.amenor.openclassrooms.msfrontend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean registerUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
