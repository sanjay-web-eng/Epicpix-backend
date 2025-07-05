package com.epicpix.epicpix.services;


import com.epicpix.epicpix.repos.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageService {
     @Autowired
     public ImageRepo imageRepo;

     //TODO : make Image service later

}
