package com.lagm.springcloud.msvc.users.repository;

import com.lagm.springcloud.msvc.users.models.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
