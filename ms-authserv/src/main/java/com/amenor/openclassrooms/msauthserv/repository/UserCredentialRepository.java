package com.amenor.openclassrooms.msauthserv.repository;

import com.amenor.openclassrooms.msauthserv.model.UserCredential;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserCredentialRepository extends MongoRepository<UserCredential, UUID> {
    Optional<UserCredential> findByUsername(String username);

}
