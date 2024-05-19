package com.amenor.openclassrooms.msauthserv.repositories;

import com.amenor.openclassrooms.msauthserv.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
