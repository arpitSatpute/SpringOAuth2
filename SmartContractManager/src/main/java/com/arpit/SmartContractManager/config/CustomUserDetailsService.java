package com.arpit.SmartContractManager.config;

import com.arpit.SmartContractManager.config.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.arpit.SmartContractManager.dao.UserRepository;
import com.arpit.SmartContractManager.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        return customUserDetails ; // Assuming CustomUserDetails is a valid UserDetails implementation
    }
}
