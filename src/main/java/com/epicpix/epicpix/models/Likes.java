package com.epicpix.epicpix.models;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;


public class Likes {


     private String username;
     private String userProfileImage;



     public String getUsername() {
          return username;
     }

     public void setUsername(String username) {
          this.username = username;
     }

     public String getUserProfileImage() {
          return userProfileImage;
     }

     public void setUserProfileImage(String userProfile) {
          this.userProfileImage = userProfile;
     }
}
