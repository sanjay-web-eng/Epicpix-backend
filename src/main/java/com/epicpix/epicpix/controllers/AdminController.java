package com.epicpix.epicpix.controllers;

import com.epicpix.epicpix.models.Images;
import com.epicpix.epicpix.models.Users;
import com.epicpix.epicpix.repos.UserRepo;
import com.epicpix.epicpix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     @PostMapping("/delete-users")
     public void deleteUser(String id){
           userService.deleteUsersForADMIN(id);
     }
     @PostMapping("/delete-image")
     public void deleteImg(String id){
          userService.deleteImageForADMIN(id);
     }
}
