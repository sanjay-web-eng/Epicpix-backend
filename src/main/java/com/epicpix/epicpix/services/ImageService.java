package com.epicpix.epicpix.services;


import com.epicpix.epicpix.models.Images;
import com.epicpix.epicpix.models.Users;
import com.epicpix.epicpix.repos.ImageRepo;
import com.epicpix.epicpix.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     public void setFile(Images images ,String username , Users DBuser){
          Images saved = imageRepo.save(images);
          DBuser.usersImages.add(saved);
          userService.saved(DBuser);
     }

     @Transactional
     public ResponseEntity<?> getAllImages(){
          List<Images> all = imageRepo.findAll();
          if(!all.isEmpty()){
               return  new ResponseEntity<>(all , HttpStatus.OK);
          }else {
               return  new ResponseEntity<>("Something went wrong" , HttpStatus.BAD_REQUEST);
          }
     }
     @Transactional
     public ResponseEntity<?> updateName(String newName ,String id  ){
          Optional<Images> DBImage = imageRepo.findById(id);
          if (DBImage.isPresent()){
               DBImage.get().setImgName(newName);
               imageRepo.save(DBImage.get());
               return new ResponseEntity<>("image name is update" , HttpStatus.OK);
          }else {
               System.out.println("Image not found !!!");
               return new ResponseEntity<>("Fail to update image name" ,HttpStatus.NOT_FOUND);
          }
     }
     @Transactional
     public ResponseEntity<?> updateImageURL(String newURL ,String id){
          Optional<Images> DBImage = imageRepo.findById(id);
          if (DBImage.isPresent()){
               DBImage.get().setImageUrl(newURL);
               imageRepo.save(DBImage.get());
               return new ResponseEntity<>("image update" , HttpStatus.OK);
          }else {
               System.out.println("Image not found !!!");
               return new ResponseEntity<>("fail to update image" ,HttpStatus.NOT_FOUND);
          }
     }

     @Transactional
     public  ResponseEntity<?> deleteImg(String id , String username){
          Optional<Users> Dbuser = userRepo.findByUsername(username);
          Optional<Images> Dbimg = imageRepo.findById(id);

          if(Dbimg.isPresent()||Dbuser.isPresent()){
               if(Dbimg.get().getOwnerUsername().equals(Dbuser.get().getUsername())){
                    imageRepo.deleteById(id);
                    Dbuser.get().usersImages.remove(Dbimg.get());
                    userRepo.save(Dbuser.get());
                    return new ResponseEntity<>("Wallpaper delete" ,HttpStatus.OK);
               }else {
                    return new ResponseEntity<>("You are not the owner of this wallpaper" ,HttpStatus.NOT_ACCEPTABLE);
               }
          }else {
               return new ResponseEntity<>("Something went wrong" , HttpStatus.NOT_FOUND);
          }

     }

     @Transactional
     public Optional<Images> getImgById(String id){
          return imageRepo.findById(id);
     }

}
