package com.epicpix.epicpix.models;


import com.epicpix.epicpix.repos.UserRepo;

import java.util.ArrayList;
import java.util.List;

public class Users {
     private String id;
     private String username;
     private String password;
     private String email;

     //TODO : add DBref when DataBase is created
     public List<?> usersImages = new ArrayList<>();
     //
     public List<?> saveImages = new ArrayList<>();
     //
     public List<?> userComments = new ArrayList<>();
     //
     public List<?> userLikes = new ArrayList<>();




     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String getUsername() {
          return username;
     }

     public void setUsername(String username) {
          this.username = username;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public List<?> getUsersImages() {
          return usersImages;
     }

     public void setUsersImages(List<?> usersImages) {
          this.usersImages = usersImages;
     }

     public List<?> getSaveImages() {
          return saveImages;
     }

     public void setSaveImages(List<?> saveImages) {
          this.saveImages = saveImages;
     }

     public List<?> getUserComments() {
          return userComments;
     }

     public void setUserComments(List<?> userComments) {
          this.userComments = userComments;
     }

     public List<?> getUserLikes() {
          return userLikes;
     }

     public void setUserLikes(List<?> userLikes) {
          this.userLikes = userLikes;
     }
}
