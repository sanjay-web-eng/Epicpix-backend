package com.epicpix.epicpix.controllers;


import com.epicpix.epicpix.models.Users;
import com.epicpix.epicpix.services.CloudinaryService;
import com.epicpix.epicpix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

     @Autowired
     public UserService userService;

     @Autowired
     private CloudinaryService cloudinaryService;

     @PostMapping("/update-username")
     public boolean updateUser(@RequestParam("newUsername")String newUsername,
                               @RequestParam("oldUsername")String oldUsername){
          return userService.updateUsername(newUsername, oldUsername);
     }

     @PostMapping("/update-email")
     public boolean updateEmail(@RequestParam("newEmail")String newEmail,
                                @RequestParam("username")String username){
          return userService.updateEamil(newEmail, username);
     }

     @PostMapping("/update-password")
     public boolean updatePassword(@RequestParam("newPassword")String newPassword,
                                   @RequestParam("username")String username){
         return userService.updatePassword(newPassword, username);
     }

     @PostMapping("/update-profileimage")
     public boolean updateProfileImage(@RequestParam("newImage")MultipartFile file,
                                       @RequestParam("username")String username) throws IOException {
          String newurl = cloudinaryService.getURL(file);
          return userService.updateProfileImage(newurl, username);
     }

     @PostMapping("/get-user/{username}")
     public ResponseEntity<?> getUser(@PathVariable String username){
         return userService.getUser(username);
     }

     @PostMapping("/save-img/{username}/{id}")
     public ResponseEntity<?> savedUser(@PathVariable String username,@PathVariable String postid){
          return userService.saveImg(username , postid);
     }
}
