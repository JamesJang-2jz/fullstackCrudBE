package com.jamesjang.fullstackcrudapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jamesjang.fullstackcrudapp.exception.UserNotFoundException;
import com.jamesjang.fullstackcrudapp.model.User;
import com.jamesjang.fullstackcrudapp.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/crud")
@CrossOrigin("http://localhost:3000/")
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
    public User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
        .map(user -> {
            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            user.setName(newUser.getName());
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

    // @PutMapping("user/{id}")  // this uses a local exception handler compared to one above
    // public User updateUser(@RequestBody User newUser, @PathVariable Long id){
    //     if (!userRepository.existsById(id)){
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
    //     }
    //     return userRepository.save(newUser);
    // }

    @DeleteMapping("user/{id}")
    public void deleteUser(@PathVariable User id){
        userRepository.delete(id);
    }

    // @GetMapping("user/{id}")
    // public User getUserById(@PathVariable Long id){
    //     return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find User " + id));
    // }

    @GetMapping("user/{id}") // this is the more global approach while the one above is for handling exceptions locally within the controller method. 
    // this one centralizes the exception handling using @ControllerAdvice in the UserNotFoundAdvice. its a global exception handler for UserNotFoundException exception types
    public User  getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

}
