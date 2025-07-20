package com.epicpix.epicpix.models;

import org.springframework.web.multipart.MultipartFile;

public class ImageReq {
     public MultipartFile file;
     public String imagName;
     public String username;
     public boolean isLikesEnable;


     public MultipartFile getFile() {
          return file;
     }

     public void setFile(MultipartFile file) {
          this.file = file;
     }

     public String getImagName() {
          return imagName;
     }

     public void setImagName(String imagName) {
          this.imagName = imagName;
     }

     public String getUsername() {
          return username;
     }

     public void setUsername(String username) {
          this.username = username;
     }

     public boolean isLikesEnable() {
          return isLikesEnable;
     }

     public void setLikesEnable(boolean likesEnable) {
          isLikesEnable = likesEnable;
     }
}
