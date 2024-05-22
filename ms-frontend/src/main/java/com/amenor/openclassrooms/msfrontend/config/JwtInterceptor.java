/*
package com.amenor.openclassrooms.msfrontend.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = (String) request.getSession().getAttribute("token");

        if (token == null && !request.getRequestURI().equals("/login") && !request.getRequestURI().startsWith("/auth/")) {
            response.sendRedirect("/login");
            return true;
        }

        if (token != null) {
            request.setAttribute("Authorization", "Bearer " + token);
        }
        return false;
    }
}*/
