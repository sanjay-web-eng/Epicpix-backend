package com.epicpix.epicpix.models;


import com.epicpix.epicpix.repos.UserRepo;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "user")
public class Users {
     private String id;
     private String username;
     private String password;
     private String email;

     public List<String> roles ;


     //TODO : add DBref when DataBase is created
     public List<Images> usersImages = new ArrayList<>();
     //
     public List<Images> saveImages = new ArrayList<>();
     //

     //  public List<?> userComments = new ArrayList<>();

     //
     public List<Images> userLikes = new ArrayList<>();


     public List<String> getRoles() {
          return roles;
     }

     public void setRoles(List<String> roles) {
          this.roles = roles;
     }

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



     public List<?> getSaveImages() {
          return saveImages;
     }




     public List<?> getUserLikes() {
          return userLikes;
     }


}
