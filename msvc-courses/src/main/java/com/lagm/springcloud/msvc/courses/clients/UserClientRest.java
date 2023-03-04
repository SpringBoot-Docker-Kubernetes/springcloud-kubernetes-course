package com.lagm.springcloud.msvc.courses.clients;

import com.lagm.springcloud.msvc.courses.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-users", url = "localhost:8001/api/v1/user")
public interface UserClientRest {
    @GetMapping("/{id}")
    User findById(@PathVariable Long id);

    @PostMapping("/")
    User save(@RequestBody User user);
}
