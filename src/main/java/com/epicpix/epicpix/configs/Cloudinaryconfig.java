package com.epicpix.epicpix.configs;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Cloudinaryconfig {
     @Bean
     public Cloudinary cloudinaryConfig() {
          Map<String, String> config = new HashMap<>();
          config.put("cloud_name", "dxozc3uww");
          config.put("api_key", "663595545771983");
          config.put("api_secret", "2oswuyYvZU2Eh_F1lDG5HrK2RmI");

          return new Cloudinary(config);
     }
}
