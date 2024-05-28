package com.amenor.openclassrooms.msauthserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsAuthservApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAuthservApplication.class, args);
	}

}
