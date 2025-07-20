package com.epicpix.epicpix.controllers;


import com.epicpix.epicpix.models.Images;
import com.epicpix.epicpix.models.Users;
import com.epicpix.epicpix.repos.UserRepo;
import com.epicpix.epicpix.services.CloudinaryService;
import com.epicpix.epicpix.services.ImageService;
import com.epicpix.epicpix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/image")
public class ImageController {

     @Autowired
     public ImageService imageService;
     @Autowired
     public UserRepo userRepo;

     @Autowired
     private UserService userService;

     @Autowired
     private CloudinaryService cloudinaryService;

     @PostMapping("/set-image")
     public ResponseEntity<?> getImage(
             @RequestParam("file") MultipartFile file,
             @RequestParam("imgName") String name,
             @RequestParam("username") String userName,
             @RequestParam("isLikesEnable") boolean isLikesEnable) throws IOException {

          Optional<Users> DBuser = userRepo.findByUsername(userName);
          if (DBuser.isEmpty()) {
               return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
          }
          if (file.isEmpty()) {
               return new ResponseEntity<>("Missing info", HttpStatus.NO_CONTENT);
          }else  if( name.isEmpty() ){
               return new ResponseEntity<>("Missing info", HttpStatus.NO_CONTENT);
          }else  if( userName.isEmpty()){
               return new ResponseEntity<>("Missing info", HttpStatus.NO_CONTENT);
          }
          // Ensure upload directory exists before saving
          Path uploadPath = Paths.get("uploads");
          if (!Files.exists(uploadPath)) {
               Files.createDirectories(uploadPath);
          }
          // Save file locally
          String fileName = System.currentTimeMillis() + file.getOriginalFilename();
          Path filePath = uploadPath.resolve(fileName);
          Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
          // Upload to cloud
          String url = cloudinaryService.getURL(file);
          if (url == null || url.isEmpty()) {
               return new ResponseEntity<>("Fail to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
          }
          System.out.println("Image URL: " + url);
          // Create and save image object
          Images images = new Images();
          images.setImageUrl(url);
          images.setLikesEnable(isLikesEnable);
          images.setOwnerProfileImage(DBuser.get().getProfileImage());
          images.setOwnerUsername(DBuser.get().getUsername());
          images.setImgName(name);
          imageService.setFile(images, DBuser.get().getUsername(), DBuser.get());
          return new ResponseEntity<>("Image uploaded successfully", HttpStatus.OK);
     }


     @PostMapping("/update-name")
     public ResponseEntity<?> getImag(@RequestParam("newName") String newName ,
                            @RequestParam("id")String id){
          return imageService.updateName(newName , id);
     }

     @PostMapping("/update-imageUrl")
     public ResponseEntity<?> getImag(@RequestParam("newFile") MultipartFile file,
                            @RequestParam("id")String id) throws IOException {
          String newUrl = cloudinaryService.getURL(file);
          return imageService.updateImageURL(newUrl , id);
     }

     //TODO : this service is incomplete don use
     @PostMapping("/delete-img")
     public  ResponseEntity<?> deleteImg(@RequestParam("id")String id , @RequestParam("username")String username ){
          return imageService.deleteImg(id , username);
     }

}
