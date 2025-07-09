package com.epicpix.epicpix.controllers;


import com.epicpix.epicpix.models.Images;
import com.epicpix.epicpix.models.Users;
import com.epicpix.epicpix.repos.UserRepo;
import com.epicpix.epicpix.services.CloudinaryService;
import com.epicpix.epicpix.services.ImageService;
import com.epicpix.epicpix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
     public Images getImage(@RequestParam("file") MultipartFile file ,
                            @RequestParam("imgName")String name ,
                            @RequestParam("username")String userName,
                            @RequestParam("isLikesEnable")boolean isLikesEnable)
             throws IOException {

          Optional<Users> DBuser = userRepo.findByUsername(userName);
          Images images = new Images();
          if (DBuser.isPresent()){
               Path uploadPath = Paths.get("uploads");
               String fileName = System.currentTimeMillis()+file.getOriginalFilename();
               Path filePath = uploadPath.resolve(fileName);
               Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
               if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
               }

               String url = cloudinaryService.getURL(file);
               System.out.println(url);
               images.setImageUrl(url);
               images.setLikesEnable(isLikesEnable);
               images.setOwnerProfileImage(DBuser.get().getProfileImage());
               images.setOwnerUsername(DBuser.get().getUsername());
               images.setImgName(name);
               imageService.getFile(images , DBuser.get().getUsername());

          }else {
               System.out.println("User not found!!!!!");
          }
          return images;
     }

     @PostMapping("/update-name")
     public boolean getImag(@RequestParam("newName") String newName ,
                            @RequestParam("id")String id){
          return imageService.updateName(newName , id);
     }

     @PostMapping("/update-imageUrl")
     public boolean getImag(@RequestParam("newFile") MultipartFile file,
                            @RequestParam("id")String id) throws IOException {
          String newUrl = cloudinaryService.getURL(file);
          return imageService.updateImageURL(newUrl , id);
     }

     @PostMapping("/delete-img/{id}")
     public void deleteImg(@PathVariable String id){
          imageService.deleteImg(id);
     }

}
