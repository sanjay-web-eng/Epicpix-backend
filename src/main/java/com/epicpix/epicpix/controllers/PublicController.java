package com.epicpix.epicpix.controllers;


import com.epicpix.epicpix.models.Users;
import com.epicpix.epicpix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
     @Autowired
     public UserService userService;

     @PostMapping("/singup")
     public void singup(@RequestBody Users users){
          userService.singupService(users);
     }
     @PostMapping("/login/{username}/{password}")
     public boolean login(@PathVariable String username ,@PathVariable String password ){
          return userService.loginService(username, password);
     }
}
