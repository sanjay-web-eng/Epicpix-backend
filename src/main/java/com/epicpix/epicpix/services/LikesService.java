package com.epicpix.epicpix.services;

import com.epicpix.epicpix.configs.ResponsClass;
import com.epicpix.epicpix.models.Images;
import com.epicpix.epicpix.models.Likes;
import com.epicpix.epicpix.models.Users;
import com.epicpix.epicpix.repos.ImageRepo;
import com.epicpix.epicpix.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
     public String likesService(String postId , String userWhoLike){
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
                    return "You like the "+DBPost.get().getImgName();
               }else {
                    return "Likes are disable for this post";
               }
          }else{
               return "You already like this post " ;
          }
     }

     @Transactional
     public String dislikeService(String postid , String username){
          Optional<Users> DBUser = userRepo.findByUsername(username);
          Optional<Images> DBPost = imageRepo.findById(postid);

          if (DBUser.isEmpty() || DBPost.isEmpty()) {
               return "User or post not found";
          }

          List<Likes> likes = DBPost.get().likes;
          if (likes == null) {
               return "No likes to remove";
          }

          if (!DBUser.get().userlikes.contains(DBPost.get())) {
               return "You didn't like this post before";
          }

          Optional<Likes> filteredUsers = likes.stream()
                  .filter(x -> x.getUsername().equals(DBUser.get().getUsername()))
                  .findFirst();

          if (filteredUsers.isPresent()) {
               DBUser.get().userlikes.remove(DBPost.get());
               DBPost.get().likes.remove(filteredUsers.get());

               imageRepo.save(DBPost.get());
               userRepo.save(DBUser.get());

               return "Disliked";
          } else {
               return "Like entry not found for this user";
          }
     }
}