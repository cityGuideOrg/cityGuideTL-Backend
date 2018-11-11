package com.cityGuideTL.backend.Controllers;

import com.cityGuideTL.backend.Models.User;
import com.cityGuideTL.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    //get all users
    @GetMapping
    @ResponseBody
    public List<User> getAll() {
        List<User> list = userRepository.findAll();
        return userRepository.findAll();
    }

    //create a user
    @PostMapping
    @ResponseBody
    public void addUser(@RequestBody User body) {
        User user = new User();
        user.setPassword(body.getPassword());
        userRepository.save(body);
    }

    //get user with specified id
    @GetMapping(path="/{id}")
    @ResponseBody
    public User getUser(@PathVariable Integer id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    //delete a user with specified id
    @DeleteMapping(path="/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable Integer id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    @PutMapping(path="/{id}")
    @ResponseBody
    public void updateUser(@RequestBody User body, @PathVariable Integer id) {
        //you cant simply replace the object because the null id is replaced also
        //you either need to get an id on the body and replace the whole user and save it or just replace the names etc.
        User userToUpdate = userRepository.getOne(id);
        userToUpdate.setFirstname(body.getFirstname());
        userToUpdate.setLastname(body.getLastname());
        userToUpdate.setEmail(body.getEmail());
        userToUpdate.setPassword(body.getPassword());
        userToUpdate.setUsername(body.getUsername());
        userRepository.save(userToUpdate);
    }
}
