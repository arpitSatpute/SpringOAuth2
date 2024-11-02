package com.security.springsecurityex.services;

import com.security.springsecurityex.entites.UserPrinciple;
import com.security.springsecurityex.entites.Users;
import com.security.springsecurityex.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServices implements UserDetailsService {

    @Autowired
    private UserRepo ur;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = ur.findByName(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrinciple(user);
    }
}
