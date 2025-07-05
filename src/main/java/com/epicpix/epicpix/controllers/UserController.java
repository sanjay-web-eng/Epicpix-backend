package com.epicpix.epicpix.controllers;


import com.epicpix.epicpix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
     @Autowired
     public UserService userService;

     //TODO : make User controller later
}
