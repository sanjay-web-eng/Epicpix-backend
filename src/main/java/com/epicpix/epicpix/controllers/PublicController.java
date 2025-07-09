package com.epicpix.epicpix.controllers;


import com.epicpix.epicpix.configs.JwtConfig;
import com.epicpix.epicpix.configs.UserDetailsConfig;
import com.epicpix.epicpix.models.Images;
import com.epicpix.epicpix.models.Users;
import com.epicpix.epicpix.repos.UserRepo;
import com.epicpix.epicpix.services.CloudinaryService;
import com.epicpix.epicpix.services.ImageService;
import com.epicpix.epicpix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/public")
public class PublicController {
     //private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

     @Autowired
     public UserService userService;
     @Autowired
     public UserRepo userRepo;
     @Autowired
     public JwtConfig jwtConfig;

     @Autowired
     private CloudinaryService cloudinaryService;

     @Autowired
     private ImageService imageService;
     @Autowired
     private UserDetailsConfig userDetailsConfig;
  

     @PostMapping("/singup")
     public ResponseEntity<String> singup(@RequestParam("username") String username ,
                                          @RequestParam("password")String password,
                                          @RequestParam("email")String email,
                                          @RequestParam("userImage")MultipartFile file) throws IOException {
          Optional<Users> findUser = userRepo.findByUsername(username);
          String url = cloudinaryService.getURL(file);
          Users users = new Users();
          users.setUsername(username);
          users.setEmail(email);
          users.setProfileImage(url);
          users.setPassword(password);
          return  userService.singupService(users);
     }

     @PostMapping("/login/{username}/{password}")
     public ResponseEntity<String> login(@PathVariable String username ,
                                         @PathVariable String password ){
          return userService.loginService(username, password);
     }

     @GetMapping("/get-all-image")
     public List<Images> getImag(){
          return imageService.getAllImages();
     }

}
