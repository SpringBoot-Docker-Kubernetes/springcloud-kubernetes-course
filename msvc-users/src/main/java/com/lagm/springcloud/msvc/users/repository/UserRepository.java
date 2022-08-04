package com.lagm.springcloud.msvc.users.repository;

import com.lagm.springcloud.msvc.users.models.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    // Method 1
    Optional<User> findByEmail(String email);

    // Methods 2: @Query
    @Query("select u from User u where u.email = ?1")
    Optional<User> findByEmail2(String email);

    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail3(@Param("email") String email);

    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    Optional<User> findByEmail4(String email);

    // Method 3: exists
    boolean existsByEmail(String email);
}
