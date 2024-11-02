package com.security.springsecurityex.controller;

import com.security.springsecurityex.entites.Users;
import com.security.springsecurityex.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UsersServices service;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users users) {
        return service.registerUser(users);
    }

    @GetMapping("/register")
    public List<Users> getUsers() {
        return service.getUsers();
    }

    @PostMapping("/login")
    public String login(@RequestBody Users users) {
        System.out.println(users);
        return service.verifyUser(users);

    }

}
