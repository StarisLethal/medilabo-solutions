package com.amenor.openclassrooms.msfrontend.proxies;

import com.amenor.openclassrooms.msfrontend.bean.JwtAuthenticationResponse;
import com.amenor.openclassrooms.msfrontend.bean.UserCredential;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "MS-AUTHSERV")
public interface AuthServiceProxy {
    @PostMapping("/auth/token")
    String login(@RequestBody UserCredential userCredential);

    @PostMapping("/auth/validate")
    JwtAuthenticationResponse validateToken(@RequestHeader("Authorization") String token);

}

