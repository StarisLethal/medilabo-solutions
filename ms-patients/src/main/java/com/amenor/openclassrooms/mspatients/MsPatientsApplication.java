package com.amenor.openclassrooms.mspatients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.amenor.openclassrooms")
public class MsPatientsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsPatientsApplication.class, args);
    }

}
