package com.amenor.openclassrooms.msfrontend.controller;


import com.amenor.openclassrooms.msfrontend.bean.AuthRequestBean;
import com.amenor.openclassrooms.msfrontend.bean.AuthResponseBean;
import com.amenor.openclassrooms.msfrontend.proxies.AuthProxy;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final AuthProxy authProxy;

    @Autowired
    public AuthController(AuthProxy authProxy) {
        this.authProxy = authProxy;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {
        try {
            AuthRequestBean requestBean = new AuthRequestBean(email, password);

            AuthResponseBean authResponseBean = authProxy.login(requestBean);

            session.setAttribute("token", authResponseBean.getToken());

            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }
}
