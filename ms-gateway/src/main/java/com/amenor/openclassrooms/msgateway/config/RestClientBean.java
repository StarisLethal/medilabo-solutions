package com.amenor.openclassrooms.msgateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestClientBean {

    @Bean
    @LoadBalanced
    public WebClient.Builder getWebClientBuilder() {return WebClient.builder();}
}
