package com.amenor.openclassrooms.mspatientnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsPatientNoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsPatientNoteApplication.class, args);
    }

}