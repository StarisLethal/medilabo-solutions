package com.amenor.openclassrooms.msfrontend.config;


import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.logging.Logger;

@Configuration
public class FeignConfig {

    private static final Logger logger = Logger.getLogger(FeignConfig.class.getName());

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                HttpServletRequest request = requestAttributes.getRequest();
                HttpSession session = request.getSession(false);
                if (session != null) {
                    String token = (String) session.getAttribute("token");
                    if (token != null) {
                        requestTemplate.header("Authorization", "Bearer " + token);
                    } else {
                        logger.warning("Authorization token is missing");
                    }
                } else {
                    logger.warning("No HTTP session found");
                }
            } else {
                logger.warning("No RequestAttributes found");
            }
        };
    }
}