package com.example.SpringSecurityAuthentication.service;

import com.example.SpringSecurityAuthentication.entity.CustomUserDetails;
import com.example.SpringSecurityAuthentication.entity.User;
import com.example.SpringSecurityAuthentication.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepositroy) {
        this.userRepository = userRepositroy;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(
                        "User Not Found"
                ));

        return new CustomUserDetails(user);
    }

}
