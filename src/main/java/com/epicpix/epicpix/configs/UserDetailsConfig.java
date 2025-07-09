package com.epicpix.epicpix.configs;

import com.epicpix.epicpix.models.Users;
import com.epicpix.epicpix.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsConfig implements UserDetailsService {

     @Autowired
     private UserRepo userRepo;

     @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          Users DBUser = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
          return User.builder()
                  .username(DBUser.getUsername())
                  .password(DBUser.getPassword())
                  .roles(DBUser.roles.toArray(new String[0]))
                  .build();
     }
}
