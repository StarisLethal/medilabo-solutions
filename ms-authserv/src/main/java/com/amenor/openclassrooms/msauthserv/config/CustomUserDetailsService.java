package com.amenor.openclassrooms.msauthserv.config;

import com.amenor.openclassrooms.msauthserv.model.UserCredential;
import com.amenor.openclassrooms.msauthserv.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> credential = userCredentialRepository.findByUsername(username);
        return credential.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException(username+ " not found"));
    }
}
