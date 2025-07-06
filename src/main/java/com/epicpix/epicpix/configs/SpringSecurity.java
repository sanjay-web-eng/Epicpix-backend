package com.epicpix.epicpix.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
     @Bean
     public SecurityFilterChain SecurityConfiguration(HttpSecurity http) throws Exception {
          return http.csrf(h->h.disable()).authorizeHttpRequests(auth->auth
                          .requestMatchers("/user/**").authenticated().anyRequest().permitAll())
                  .sessionManagement(session->session.disable())
                  .httpBasic(Customizer.withDefaults()).build();
     }
}
