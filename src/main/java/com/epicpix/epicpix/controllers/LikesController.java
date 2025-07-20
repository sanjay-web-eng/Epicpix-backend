package com.epicpix.epicpix.controllers;


import com.epicpix.epicpix.models.HelperClass;
import com.epicpix.epicpix.models.Likes;
import com.epicpix.epicpix.services.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikesController {

     @Autowired
     private LikesService likesService;

     @PostMapping("/set-like")
     public ResponseEntity<?> setLike(@RequestBody HelperClass user){
          return likesService.likesService(user.getPostId(), user.getUsername());
     }

     @PostMapping("/remove-like")
     public ResponseEntity<?> ReLike(@RequestBody HelperClass user){
          return  likesService.dislikeService(user.getPostId(), user.getUsername());
     }
}
