package com.epicpix.epicpix.models;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Document(collection = "wallpapers")
public class Images {
     private String id;
     public String imageUrl;
     public boolean isLikesEnable;

     //TODO : add DBref when DataBase is created
     public List<?> Likes= new ArrayList<>();

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
          return Likes;
     }

     public void setLikes(List<?> likes) {
          Likes = likes;
     }
}
