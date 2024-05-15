package com.amenor.openclassrooms.mspatientnote;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties
public class MsPatientNoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsPatientNoteApplication.class, args);
    }


}