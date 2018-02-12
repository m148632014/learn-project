package org.mfm.learn.springhateoas.repository;


import java.util.Optional;

import org.mfm.learn.springhateoas.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
