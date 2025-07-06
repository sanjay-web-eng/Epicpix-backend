package com.epicpix.epicpix.controllers;


import com.epicpix.epicpix.models.Images;
import com.epicpix.epicpix.repos.UserRepo;
import com.epicpix.epicpix.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/image")
public class ImageController {

     @Autowired
     public ImageService imageService;
//C:\Users\Acer\Downloads\epicpix\epicpix\src\main\resources\static
     @Autowired
     public Images images;
     @PostMapping("/get-image")
     public Path getImage(@RequestParam("file") MultipartFile file) throws IOException {
          String fileName = file.getOriginalFilename();
          Path uploadPath = Paths.get("epicpix/epicpix/src/main/resources/static/uploads");

          if (!Files.exists(uploadPath)) {
               Files.createDirectories(uploadPath);
          }

          Path filePath = uploadPath.resolve(fileName);
          Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

          return uploadPath;
     }

     //TODO : make Image controller later
}
