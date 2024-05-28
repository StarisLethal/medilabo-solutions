package com.amenor.openclassrooms.msfrontend.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestBean {
    private String email;
    private String password;
    private boolean rememberMe;
}
