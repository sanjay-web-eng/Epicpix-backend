package com.epicpix.epicpix.services;


import com.epicpix.epicpix.models.Users;
import com.epicpix.epicpix.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class UserService {
     @Autowired
     public UserRepo userRepo ;


     public void singupService (Users users){
          try {
               if(users != null){
                    users.setRoles(Arrays.asList("USER"));
                    userRepo.save(users);
               }else {
                    System.out.println("user OBj don't get it");
               }
          } catch (Exception e) {
               throw new RuntimeException(e);
          }
     }
     public boolean loginService (String username , String password){
          try {
               Optional<Users> DBusername = userRepo.findByUsername(username);
               if (DBusername.isPresent()){
                    if(DBusername.get().getPassword().equals(password)){
                         System.out.println("your are login "+" "+ DBusername.get().getUsername());
                         return true;
                    }else {
                         return false;
                    }
               }else {
                    System.out.println("user not found");
                    return false;
               }
          } catch (Exception e) {
               throw new RuntimeException(e);
          }
     }

     //TODO : make User service later
}
