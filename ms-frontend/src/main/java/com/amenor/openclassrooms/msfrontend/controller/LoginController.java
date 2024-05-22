package com.amenor.openclassrooms.msfrontend.controller;

import com.amenor.openclassrooms.msfrontend.bean.JwtAuthenticationResponse;
import com.amenor.openclassrooms.msfrontend.bean.UserCredential;
import com.amenor.openclassrooms.msfrontend.proxies.AuthServiceProxy;
import feign.FeignException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {


    private final AuthServiceProxy authServiceProxy;

    public LoginController(AuthServiceProxy authServiceProxy) {
        this.authServiceProxy = authServiceProxy;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername(username);
        userCredential.setPassword(password);

        try {
            // Get the JWT token from the auth service
            String jwtResponse = authServiceProxy.login(userCredential);

            // Validate the token
            JwtAuthenticationResponse validationResponse = authServiceProxy.validateToken(jwtResponse);

            // Check if the token is valid
            if (validationResponse != null && validationResponse.isValid()) {
                // Store token in session if valid
                session.setAttribute("token", jwtResponse);
                return "redirect:/home";
            } else {
                // Handle invalid token case (e.g., redirect to login with an error message)
                return "redirect:/login?error=invalid_token";
            }
        } catch (FeignException e) {
            // Log the error for debugging
            System.err.println("Error during login: " + e.getMessage());
            // Handle different HTTP status codes
            if (e.status() == 403) {
                return "redirect:/login?error=forbidden";
            } else {
                return "redirect:/login?error=unknown";
            }
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showLoginPage() {
        System.out.println("this is login page");
        return "login";
    }
}

