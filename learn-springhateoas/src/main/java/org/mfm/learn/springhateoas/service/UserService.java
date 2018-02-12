package org.mfm.learn.springhateoas.service;


import java.util.Optional;

import org.mfm.learn.springhateoas.model.User;
import org.mfm.learn.springhateoas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
