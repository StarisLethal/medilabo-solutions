package com.amenor.openclassrooms.msauthserv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredential {

    @Id
    private String id;
    private String username;
    private String password;

}
