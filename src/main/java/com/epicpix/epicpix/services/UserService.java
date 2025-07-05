package com.epicpix.epicpix.services;


import com.epicpix.epicpix.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
     @Autowired
     public UserRepo userRepo ;

     //TODO : make User service later
}
