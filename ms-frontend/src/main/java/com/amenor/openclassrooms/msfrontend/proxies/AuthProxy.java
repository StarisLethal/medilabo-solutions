package com.amenor.openclassrooms.msfrontend.proxies;

import com.amenor.openclassrooms.msfrontend.bean.AuthRequestBean;
import com.amenor.openclassrooms.msfrontend.bean.AuthResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("MS-GATEWAY")
public interface AuthProxy {

    @PostMapping("/auth/login")
    AuthResponseBean login(@RequestBody AuthRequestBean authRequest);

    @GetMapping("/auth/validate/{token}")
    boolean validate(@PathVariable String token);
}
