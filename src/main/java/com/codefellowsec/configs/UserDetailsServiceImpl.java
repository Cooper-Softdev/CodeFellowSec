package com.codefellowsec.configs;

import com.codefellowsec.models.ApplicationUser;
import com.codefellowsec.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository UserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = UserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Error: username incorrect or not found");
        }
        return user;
    }
}
