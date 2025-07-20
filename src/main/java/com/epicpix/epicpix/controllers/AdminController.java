package com.epicpix.epicpix.controllers;

import com.epicpix.epicpix.models.Images;
import com.epicpix.epicpix.models.Users;
import com.epicpix.epicpix.repos.UserRepo;
import com.epicpix.epicpix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
     @Autowired
     private UserService userService;

     @GetMapping("/all-users")
     public List<Users> getAllUser(){
          return userService.getAllUsersForADMIN();
     }
     @GetMapping("/all-img")
     public List<Images> getAllimg(){
          return userService.getAllImageForADMIN();
     }
     @PostMapping("/delete-users/{id}")
     public void deleteUser(@PathVariable String id){
           userService.deleteUsersForADMIN(id);
     }
     @PostMapping("/delete-image/{id}")
     public void deleteImg(@PathVariable  String id){
          userService.deleteImageForADMIN(id);
     }
}
