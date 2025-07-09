package com.epicpix.epicpix.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
     @Autowired
     private Cloudinary cloudinary;

     public String getURL(MultipartFile file) throws IOException {
          Map upload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
          return upload.get("secure_url").toString();
     }
}
