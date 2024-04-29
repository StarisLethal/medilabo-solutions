package com.amenor.openclassrooms.msfrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@FeignClient
public class MsFrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsFrontendApplication.class, args);
    }

}
