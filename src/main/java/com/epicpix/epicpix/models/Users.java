package com.epicpix.epicpix.models;


import com.epicpix.epicpix.repos.UserRepo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "user")
public class Users {
     @Id
     private String id;

     @Indexed(unique = true)
     private String username;

     private String password;
     private String email;
     public String ProfileImage ;

     public List<String> roles ;

     @DBRef
     public List<Images> usersImages = new ArrayList<>();
     @DBRef
     public List<Images> saveImages = new ArrayList<>();

     @DBRef
     public List<Images> userlikes = new ArrayList<>();




     //  public List<?> userComments = new ArrayList<>();

     public List<String> getRoles() {
          return roles;
     }

     public String getProfileImage() {
          return ProfileImage;
     }

     public void setProfileImage(String profileImage) {
          ProfileImage = profileImage;
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

}
