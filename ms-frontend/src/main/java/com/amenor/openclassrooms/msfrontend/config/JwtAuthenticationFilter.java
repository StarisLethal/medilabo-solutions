package com.amenor.openclassrooms.msfrontend.config;

import com.amenor.openclassrooms.msfrontend.proxies.AuthProxy;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthProxy authProxy;

    public JwtAuthenticationFilter(AuthProxy authProxy) {
        this.authProxy = authProxy;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && authProxy.validate(token.replace("Bearer ", ""))) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(token, null, new ArrayList<>()));
        }
        filterChain.doFilter(request, response);
    }
}