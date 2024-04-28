package com.amenor.openclassrooms.mspatients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.amenor.openclassrooms")
@EnableDiscoveryClient
public class MsPatientsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsPatientsApplication.class, args);
    }

}
