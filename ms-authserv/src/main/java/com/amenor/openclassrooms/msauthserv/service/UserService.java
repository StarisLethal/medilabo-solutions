package com.amenor.openclassrooms.msauthserv.service;

import com.amenor.openclassrooms.msauthserv.model.User;


public interface UserService {

    User findByEmail(String email);

    User save(User user);

    User update(String email, User user);

    void delete(String email);
}
