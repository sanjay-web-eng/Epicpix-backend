package com.epicpix.epicpix.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

     private final Cloudinary cloudinary;

     public CloudinaryService(Cloudinary cloudinary) {
          this.cloudinary = cloudinary;
     }

     public String getURL(MultipartFile file) throws IOException {
          // Upload the file
          Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

          // Get public_id
          String publicId = uploadResult.get("public_id").toString();
          String format = uploadResult.get("format").toString(); // like jpg, png, etc.

          // Generate downloadable URL with fl_attachment flag
          String downloadUrl = cloudinary.url()
                  .transformation(new Transformation().flags("attachment:download"))
                  .generate(publicId + "." + format);

          return downloadUrl;
     }
}
