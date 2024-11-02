package com.security.springsecurityex.services;

import com.security.springsecurityex.entites.Users;
import com.security.springsecurityex.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsersServices {

    @Autowired
    private UserRepo ur;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users registerUser(Users users) {
        users.setPassword(encoder.encode(users.getPassword()));
        return ur.save(users);
    }

    public List<Users> getUsers() {
        return ur.findAll();
    }

    public String verifyUser(Users users) {
        Authentication auth =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(users.getName(), users.getPassword()));

        if(auth.isAuthenticated()) {
            return jwtService.generateToken(users.getName());
        }
        return "Failed";

    }
}
