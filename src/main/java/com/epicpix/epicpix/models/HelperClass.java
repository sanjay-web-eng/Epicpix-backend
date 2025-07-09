package com.epicpix.epicpix.models;


import org.springframework.stereotype.Component;

@Component
public class HelperClass {
     private String username;
     private String postId;


     public String getUsername() {
          return username;
     }

     public void setUsername(String username) {
          this.username = username;
     }

     public String getPostId() {
          return postId;
     }

     public void setPostId(String postId) {
          this.postId = postId;
     }
}
