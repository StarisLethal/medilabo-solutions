package com.amenor.openclassrooms.msauthserv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("db_user")
public class UserCredential {

    @Id
    private String id;
    private String username;
    private String password;

}
