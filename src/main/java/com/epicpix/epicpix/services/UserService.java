package com.epicpix.epicpix.services;

import com.epicpix.epicpix.configs.JwtConfig;
import com.epicpix.epicpix.configs.UserDetailsConfig;
import com.epicpix.epicpix.models.Images;
import com.epicpix.epicpix.models.Users;
import com.epicpix.epicpix.repos.ImageRepo;
import com.epicpix.epicpix.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
     @Autowired
     public UserRepo userRepo ;
     @Autowired
     public ImageRepo imageRepo;
     @Autowired
     private UserDetailsConfig userDetailsConfig;

     @Autowired
     public JwtConfig jwtConfig;
     private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

     @Transactional
     public ResponseEntity<String> singupService (Users users){
          Optional<Users> findUser = userRepo.findByUsername(users.getUsername());
          if(users != null){
               if(!findUser.isPresent()){
                    users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
                    users.setRoles(Arrays.asList("USER"));
                    userRepo.save(users);
                    UserDetails userDetails = userDetailsConfig.loadUserByUsername(users.getUsername());
                    String jwt = jwtConfig.generateToken(userDetails.getUsername());
                    System.out.println(jwt);
                    return new ResponseEntity<>(jwt , HttpStatus.CREATED);
               }else {
                    return new ResponseEntity<>( "User already exist try new name" , HttpStatus.IM_USED);
               }
          }else {
               return new ResponseEntity<>("info is missing " , HttpStatus.BAD_REQUEST);
          }
     }

     @Transactional
     public ResponseEntity<String> singupAsADMINService (Users users){
          Optional<Users> findUser = userRepo.findByUsername(users.getUsername());
          if(users != null){
               if(!findUser.isPresent()){
                    users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
                    users.setRoles(Arrays.asList("ADMIN","USER"));
                    userRepo.save(users);
                    UserDetails userDetails = userDetailsConfig.loadUserByUsername(users.getUsername());
                    String jwt = jwtConfig.generateToken(userDetails.getUsername());
                    System.out.println(jwt);
                    return new ResponseEntity<>(jwt , HttpStatus.CREATED);
               }else {
                    return new ResponseEntity<>( "User already exist try new name" , HttpStatus.IM_USED);
               }
          }else {
               return new ResponseEntity<>("info is missing " , HttpStatus.BAD_REQUEST);
          }
     }

     @Transactional
     public ResponseEntity<String> loginService(String username, String password) {
          Optional<Users> userOpt = userRepo.findByUsername(username);

          // User not found
          if (userOpt.isEmpty()) {
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
          }

          Users user = userOpt.get();

          // Incorrect password
          if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
               return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
          }

          // Generate JWT token
          UserDetails userDetails = userDetailsConfig.loadUserByUsername(user.getUsername());
          String jwt = jwtConfig.generateToken(userDetails.getUsername());
          System.out.println(jwt);

          return ResponseEntity.ok(jwt);
     }


     @Transactional
     public void saved(Users users ){
          userRepo.save(users);
     }

     @Transactional
     public ResponseEntity<String> updateUsername(String newUsername ,String oldusername){
          Optional<Users> DBUser = userRepo.findByUsername(oldusername);
          Optional<Users> byUsername = userRepo.findByUsername(newUsername);
          if (DBUser.isPresent()) {
               if(!byUsername.isPresent()){
                    DBUser.get().setUsername(newUsername);
                    userRepo.save(DBUser.get());
                    String JwtToken = jwtConfig.generateToken(DBUser.get().getUsername());
                    return new ResponseEntity<>(JwtToken , HttpStatus.OK);
               }else {
                    return new ResponseEntity<>("this username already exist try new name" , HttpStatus.IM_USED);
               }
          }else {
               return new ResponseEntity<>("Fail to update username" , HttpStatus.NOT_FOUND);
          }
     }

     //TODO : this service has so errror
     @Transactional
     public ResponseEntity<String> updatePassword(String newPassword ,String username){
          Optional<Users> DBUser = userRepo.findByUsername(username);
          if (DBUser.isPresent()) {
               DBUser.get().setPassword(newPassword);
               userRepo.save(DBUser.get());
               return new ResponseEntity<>("Password update" ,HttpStatus.OK);
          }else {
               return new ResponseEntity<>("Fail to update password" ,HttpStatus.NOT_FOUND);
          }
     }

     @Transactional
     public ResponseEntity<String> updateEmail(String email ,String username){
          Optional<Users> DBUser = userRepo.findByUsername(username);
          if (DBUser.isPresent()) {
               DBUser.get().setEmail(email);
               userRepo.save(DBUser.get());
               String jwt = jwtConfig.generateToken(DBUser.get().getUsername());
               return new ResponseEntity<>(jwt ,HttpStatus.OK);
          }else {
               return new ResponseEntity<>("Fail to update email" ,HttpStatus.NOT_FOUND);
          }
     }
     @Transactional
     public ResponseEntity<String> updateProfileImage(String newUrl ,String username){
          Optional<Users> DBUser = userRepo.findByUsername(username);
          if (DBUser.isPresent()) {
               int imgCount = DBUser.map(user -> user.usersImages.size()).orElse(0);

               DBUser.get().setProfileImage(newUrl);
               userRepo.save(DBUser.get());
               String jwt = jwtConfig.generateToken(DBUser.get().getUsername());
               return new ResponseEntity<>(jwt ,HttpStatus.OK);
          }else {
               return new ResponseEntity<>("Fail to update profile image" ,HttpStatus.NOT_FOUND);
          }
     }

//     @Transactional
//     public ResponseEntity<String> deleteA(Images images , String username){
//          Optional<Users> DBUser = userRepo.findByUsername(username);
//          if (DBUser.isPresent()) {
//               DBUser.get().usersImages.remove(images);
//               userRepo.save(DBUser.get());
//               return new ResponseEntity<>("image" ,HttpStatus.OK);
//          }else {
//               return false;
//          }
//     }

     @Transactional
     public ResponseEntity<?> getUser(String tokem){
          String username = jwtConfig.extractUsername(tokem);
          Optional<Users> DBUsername = userRepo.findByUsername(username);
          if (DBUsername.isPresent()) {
               return new ResponseEntity<>(DBUsername.get() , HttpStatus.OK) ;
          }else {
               return new ResponseEntity<>("User not found" , HttpStatus.BAD_REQUEST);
          }
     }


     @Transactional
     public ResponseEntity<?> saveImg(String username , String postid){
          Optional<Users> DBUser = userRepo.findByUsername(username);
          Optional<Images> DBPost = imageRepo.findById(postid);
          if (DBUser.isPresent() && DBPost.isPresent()) {
               if (!DBUser.get().saveImages.contains(DBPost.get())) {
                    DBUser.get().saveImages.add(DBPost.get());
                    userRepo.save(DBUser.get());
                    return new ResponseEntity<>("Saved" , HttpStatus.OK) ;
               }else {
                    return new ResponseEntity<>("You already saved this post" , HttpStatus.IM_USED) ;
               }
          }else {
               return new ResponseEntity<>("Something went wrong" , HttpStatus.BAD_REQUEST);
          }
     }
     @Transactional
     public ResponseEntity<?> removeSavedImg(String username , String postid){
          Optional<Users> DBUser = userRepo.findByUsername(username);
          Optional<Images> DBPost = imageRepo.findById(postid);
          if (DBUser.isPresent() && DBPost.isPresent()) {
                    DBUser.get().saveImages.remove(DBPost.get());
                    userRepo.save(DBUser.get());
                    return new ResponseEntity<>("Removed" , HttpStatus.OK) ;
          }else {
               return new ResponseEntity<>("Something went wrong" , HttpStatus.BAD_REQUEST);
          }

     }
     // Some Service for admin

     @Transactional
     public List<Users> getAllUsersForADMIN(){
          return userRepo.findAll();
     }
     @Transactional
     public List<Images> getAllImageForADMIN(){
          return imageRepo.findAll();
     }
     @Transactional
     public void deleteUsersForADMIN(String id){
           userRepo.deleteById(id);
     }
     @Transactional
     public void deleteImageForADMIN(String id){
          imageRepo.deleteById(id);
     }
}
