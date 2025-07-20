package com.epicpix.epicpix.configs;


import com.epicpix.epicpix.filters.JwtFilter;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
     @Autowired
     private UserDetailsConfig userDetailsConfig;
     @Autowired
     private JwtFilter jwtFilter;

     @Bean
     public SecurityFilterChain SecurityConfiguration(HttpSecurity http) throws Exception {
          return http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth->auth
                          .requestMatchers(HttpMethod.OPTIONS , "/**").permitAll()
                          .requestMatchers("/image/**" , "/user/**" ,"/likes/**").authenticated()
                          .requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().permitAll())
                  .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                  .addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class).build();
     }

     @Autowired
     private void AuthenticationManage(AuthenticationManagerBuilder auth) throws Exception {
          auth.userDetailsService(userDetailsConfig).passwordEncoder(passwordEncoder());
     }
     @Bean
     public PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder();
     }

     @Bean
     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
          return config.getAuthenticationManager();
     }
}
