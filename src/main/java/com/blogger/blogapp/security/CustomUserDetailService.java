package com.blogger.blogapp.security;


import com.blogger.blogapp.entities.User;
import com.blogger.blogapp.exception.ResourceNotFoundException;
import com.blogger.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //it will fetch user from db by username.
        User user = userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User", "email", Long.parseLong(username)));

        return user;
    }
}
