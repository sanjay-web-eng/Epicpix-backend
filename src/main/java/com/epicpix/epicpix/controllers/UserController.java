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
     public ResponseEntity<String> updateUser(@RequestParam("newUsername")String newUsername,
                               @RequestParam("oldUsername")String oldUsername){
          return userService.updateUsername(newUsername, oldUsername);
     }

     @PostMapping("/update-email")
     public ResponseEntity<String> updateEmail(@RequestParam("newEmail")String newEmail,
                                @RequestParam("username")String username){
          return userService.updateEmail(newEmail, username);
     }

     //TODO : this end point has some problems don't use for now
     @PostMapping("/update-password")
     public ResponseEntity<String> updatePassword(@RequestParam("newPassword")String newPassword,
                                   @RequestParam("username")String username){
         return userService.updatePassword(newPassword, username);
     }

     @PostMapping("/update-profileimage")
     public ResponseEntity<String> updateProfileImage(@RequestParam("newImage")MultipartFile file,
                                       @RequestParam("username")String username) throws IOException {
          String newurl = cloudinaryService.getURL(file);
          return userService.updateProfileImage(newurl, username);
     }



     @PostMapping("/save-img/{username}/{id}")
     public ResponseEntity<?> savedUser(@PathVariable String username,@PathVariable String id){
          return userService.saveImg(username , id);
     }
     @PostMapping("/remove-save-img/{username}/{id}")
     public ResponseEntity<?> removeSavedUser(@PathVariable String username,@PathVariable String id){
          return userService.removeSavedImg(username , id);
     }
}
