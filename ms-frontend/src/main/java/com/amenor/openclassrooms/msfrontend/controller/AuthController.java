package com.amenor.openclassrooms.msfrontend.controller;


import com.amenor.openclassrooms.msfrontend.bean.AuthRequestBean;
import com.amenor.openclassrooms.msfrontend.bean.AuthResponseBean;
import com.amenor.openclassrooms.msfrontend.proxies.AuthProxy;
import feign.FeignException;
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

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("AuthRequest", new AuthRequestBean());
        return "login";
    }
    @PostMapping("/auth")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("rememberMe") boolean rememberMe, HttpSession session) {
        AuthRequestBean requestBean = new AuthRequestBean(username, password, rememberMe);
        try {
            AuthResponseBean responseBean = authProxy.login(requestBean);
            session.setAttribute("jwt", responseBean.getToken());
            return "redirect:/home";
        } catch (FeignException e) {
            return "redirect:/login?error";
        }
    }
}
