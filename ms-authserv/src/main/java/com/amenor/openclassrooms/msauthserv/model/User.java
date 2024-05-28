package com.amenor.openclassrooms.msauthserv.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("db_user")
public class User {
    @Id
    private String email;
    private String password;
}
