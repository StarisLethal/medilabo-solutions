package com.amenor.openclassrooms.msfrontend.repositories;

import com.amenor.openclassrooms.msfrontend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
