package com.epicpix.epicpix.services;


import com.epicpix.epicpix.models.Images;
import com.epicpix.epicpix.models.Users;
import com.epicpix.epicpix.repos.ImageRepo;
import com.epicpix.epicpix.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class ImageService {
     @Autowired
     public ImageRepo imageRepo;
     @Autowired
     public UserRepo userRepo;
     @Autowired
     public UserService userService;

     @Transactional
     public void getFile(Images images ,String username){
          Optional<Users> byUsername = userRepo.findByUsername(username);
          Images saved = imageRepo.save(images);
          byUsername.get().usersImages.add(saved);
          userService.saved(byUsername.get());
     }
     @Transactional
     public List<Images> getAllImages(){
          List<Images> all = imageRepo.findAll();
          return all;
     }
     @Transactional
     public boolean updateName(String newName ,String id  ){
          Optional<Images> DBImage = imageRepo.findById(id);
          if (DBImage.isPresent()){
               DBImage.get().setImgName(newName);
               imageRepo.save(DBImage.get());
               return true;
          }else {
               System.out.println("Image not found !!!");
               return false;
          }
     }
     @Transactional
     public boolean updateImageURL(String newURL ,String id){
          Optional<Images> DBImage = imageRepo.findById(id);
          if (DBImage.isPresent()){
               DBImage.get().setImageUrl(newURL);
               imageRepo.save(DBImage.get());
               return true;
          }else {
               System.out.println("Image not found !!!");
               return false;
          }
     }

     @Transactional
     public void deleteImg(String id){
          imageRepo.deleteById(id);
     }

}
