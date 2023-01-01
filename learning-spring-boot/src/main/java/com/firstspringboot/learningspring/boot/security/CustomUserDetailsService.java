package com.firstspringboot.learningspring.boot.security;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.firstspringboot.learningspring.boot.entries.User;
import com.firstspringboot.learningspring.boot.repository.userRepository;

@Service
public class CustomUserDetailsService implements Serializable, UserDetailsService {

    private userRepository userrepository;

    public CustomUserDetailsService(userRepository userrepository) {
        this.userrepository = userrepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {

        User user = userrepository.findByUserNameOrEmail(userNameOrEmail, userNameOrEmail).orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : "+userNameOrEmail));
        Set<GrantedAuthority> authorities = user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

}
