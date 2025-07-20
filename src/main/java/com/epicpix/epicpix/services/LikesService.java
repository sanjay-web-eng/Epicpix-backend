package com.epicpix.epicpix.services;

import com.epicpix.epicpix.configs.ResponsClass;
import com.epicpix.epicpix.models.Images;
import com.epicpix.epicpix.models.Likes;
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
public class LikesService {

     @Autowired
     private UserRepo userRepo;
     @Autowired
     private UserService userService;
     @Autowired
     private ImageRepo imageRepo;
     @Autowired
     private ResponsClass responsClass;

     @Transactional
     public ResponseEntity<?> likesService(String postId , String userWhoLike){
          Optional<Users> DBUserWhoLikeIt = userRepo.findByUsername(userWhoLike);
          Optional<Images> DBPost = imageRepo.findById(postId);
          List<Likes> likes = DBPost.get().likes;
          Optional<Likes> filteredUsers = likes.stream().filter(x -> x.getUsername().equals(DBUserWhoLikeIt.get().getUsername())).findAny();

          Likes like = new Likes();
          like.setUserProfileImage(DBUserWhoLikeIt.get().getProfileImage());
          like.setUsername(DBUserWhoLikeIt.get().getUsername());
          if(!filteredUsers.isPresent()){
               if(DBPost.get().isLikesEnable){
                    DBPost.get().likes.add(like);
                    imageRepo.save(DBPost.get());
                    DBUserWhoLikeIt.get().userlikes.add(DBPost.get());
                    userRepo.save(DBUserWhoLikeIt.get());
                    return new ResponseEntity<>("You like the "+DBPost.get().getImgName() , HttpStatus.OK);
               }else {
                    return new ResponseEntity<>("Likes are disable for this post" , HttpStatus.NOT_ACCEPTABLE);
               }
          }else{
               return new ResponseEntity<>("You already like this post " , HttpStatus.IM_USED) ;
          }
     }

     @Transactional
     public ResponseEntity<?> dislikeService(String postid , String username){
          Optional<Users> DBUser = userRepo.findByUsername(username);
          Optional<Images> DBPost = imageRepo.findById(postid);
          if (DBUser.isEmpty() || DBPost.isEmpty()) {
               return new ResponseEntity<>("User or post not found" , HttpStatus.NOT_FOUND);
          }
          List<Likes> likes = DBPost.get().likes;
          if (likes == null) {
               return new ResponseEntity<>("No likes to remove" , HttpStatus.NO_CONTENT);
          }
          if (!DBUser.get().userlikes.contains(DBPost.get())) {
               return new ResponseEntity<>("You didn't like this post before" ,HttpStatus.FORBIDDEN);
          }
          Optional<Likes> filteredUsers = likes.stream()
                  .filter(x -> x.getUsername().equals(DBUser.get().getUsername()))
                  .findFirst();
          if (filteredUsers.isPresent()) {
               DBUser.get().userlikes.remove(DBPost.get());
               DBPost.get().likes.remove(filteredUsers.get());

               imageRepo.save(DBPost.get());
               userRepo.save(DBUser.get());

               return new ResponseEntity<>("Disliked",HttpStatus.OK);
          } else {
               return new ResponseEntity<>("Like entry not found for this user" , HttpStatus.BAD_REQUEST);
          }
     }
}