package com.epicpix.epicpix.services;


import com.epicpix.epicpix.models.Images;
import com.epicpix.epicpix.repos.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageService {
     @Autowired
     public ImageRepo imageRepo;

     public void getFile(Images images){
          imageRepo.save(images);
     }

     //TODO : make Image service later
}
