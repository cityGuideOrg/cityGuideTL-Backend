package com.cityGuideTL.backend.Controllers;

import com.cityGuideTL.backend.Models.User;
import com.cityGuideTL.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    //get all users
    @GetMapping(path="/user")
    @ResponseBody
    public List<User> getAll() {
        List<User> list = userRepository.findAll();
        return list;
    }

    //create a user
    @PostMapping(path="/user")
    @ResponseBody
    public void addUser(@RequestBody User body) {
        User user = new User();
        user.setPassword(body.getPassword());
        userRepository.save(body);
    }

    //get user with specified id
    @GetMapping(path="/user/{id}")
    @ResponseBody
    public User getUser(@RequestParam Integer id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    //delete a user with specified id
    @DeleteMapping(path="/user/{id}")
    @ResponseBody
    public void deleteUser(@RequestParam Integer id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    @PutMapping(path="/user/{id}")
    @ResponseBody
    public void updateUser(@RequestBody User body) {
        User user = body;
        userRepository.save(user);
    }
}
