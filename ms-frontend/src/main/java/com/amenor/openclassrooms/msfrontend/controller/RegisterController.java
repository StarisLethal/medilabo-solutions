package com.amenor.openclassrooms.msfrontend.controller;

import com.amenor.openclassrooms.msfrontend.model.User;
import com.amenor.openclassrooms.msfrontend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean isUserSaved = userService.registerUser(user);
        if (isUserSaved) {
            return ResponseEntity.ok("User registered successfully");
        }else {
            return ResponseEntity.status(500).body("User could not be registered!");
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
