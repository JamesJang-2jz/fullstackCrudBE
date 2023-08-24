package com.jamesjang.fullstackcrudapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamesjang.fullstackcrudapp.model.User;
import com.jamesjang.fullstackcrudapp.repository.UserRepository;

@RestController
@RequestMapping("/crud")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @PutMapping("user/{id}")
    public void updateUser(@PathVariable User id){
        
    }

}
