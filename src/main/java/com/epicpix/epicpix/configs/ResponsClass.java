package com.epicpix.epicpix.configs;

import org.springframework.stereotype.Component;

@Component
public class ResponsClass {
     public String massage;

     public String getMassage() {
          return massage;
     }

     public ResponsClass setMassage(String massage) {
          this.massage = massage;
          return null;
     }
}
