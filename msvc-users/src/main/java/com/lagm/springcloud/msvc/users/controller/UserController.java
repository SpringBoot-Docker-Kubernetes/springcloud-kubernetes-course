package com.lagm.springcloud.msvc.users.controller;

import com.lagm.springcloud.msvc.users.models.entity.User;
import com.lagm.springcloud.msvc.users.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<User> oUser = userService.findById(id);
        if (oUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(oUser.get());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        User userSaved = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable("id") Long id) {
        Optional<User>optUserDB = userService.findById(id);
        if (optUserDB.isPresent()) {
            User userDB = optUserDB.get();
            userDB.setName(user.getName());
            userDB.setEmail(user.getEmail());
            userDB.setPassword(user.getPassword());
            userDB.setCreateAt(user.getCreateAt());
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<User>optUserDB = userService.findById(id);
        if (optUserDB.isPresent()) {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
