package com.epicpix.epicpix.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Document(collection = "wallpapers")
public class Images {
     @Id
     private String id;
     public String imageUrl;
     public String ImgName;
     public String ownerUsername;
     public String ownerProfileImage;
     public boolean isLikesEnable;



     public List<Likes> likes= new ArrayList<Likes>();

     public List<Likes> S(){
         return likes;
     }

     public String getImgName() {
          return ImgName;
     }

     public void setImgName(String imgName) {
          ImgName = imgName;
     }

     public String getOwnerUsername() {
          return ownerUsername;
     }

     public void setOwnerUsername(String ownerUsername) {
          this.ownerUsername = ownerUsername;
     }

     public String getOwnerProfileImage() {
          return ownerProfileImage;
     }

     public void setOwnerProfileImage(String ownerProfileImage) {
          this.ownerProfileImage = ownerProfileImage;
     }

     public String getId() {
          return id;
     }

     public void setId(String id) {
          this.id = id;
     }

     public String getImageUrl() {
          return imageUrl;
     }

     public void setImageUrl(String imageUrl) {
          this.imageUrl = imageUrl;
     }

     public boolean isLikesEnable() {
          return isLikesEnable;
     }

     public void setLikesEnable(boolean likesEnable) {
          isLikesEnable = likesEnable;
     }

     public List<?> getLikes() {
          return likes;
     }

     public void setLikes(List<Likes> likes) {
          this.likes = likes;
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          Images images = (Images) o;
          return Objects.equals(this.id, images.id); // or whatever your ID field is
     }

     @Override
     public int hashCode() {
          return Objects.hash(id);
     }

}
