package com.epicpix.epicpix.controllers;


import com.epicpix.epicpix.repos.ImageRepo;
import com.epicpix.epicpix.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ImageController {
     @Autowired
     public ImageService imageService;


     //TODO : make User controller later
}
