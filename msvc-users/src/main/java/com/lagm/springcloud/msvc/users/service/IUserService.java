package com.lagm.springcloud.msvc.users.service;

import com.lagm.springcloud.msvc.users.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(Long id);
    void deleteById(Long id);
}
