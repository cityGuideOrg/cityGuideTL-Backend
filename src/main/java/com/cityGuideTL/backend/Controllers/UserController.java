package com.cityGuideTL.backend.Controllers;

import com.cityGuideTL.backend.Models.UserModel;
import com.cityGuideTL.backend.Repository.UserRepository;
import com.cityGuideTL.backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//to fix validations
@RestController
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private UserService userService = new UserService();
    //get all users
    @GetMapping
    @ResponseBody
    public List<UserModel> getAll() {
        return userService.getAll();
    }

    //create a user
    public void addUser(@RequestBody UserModel body) {
       userService.addUser(body);
    }

    //get user with specified id
    @GetMapping(path="/{id}")
    @ResponseBody
    public UserModel getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    //delete a user with specified id
    @DeleteMapping(path="/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping(path="/{id}")
    @ResponseBody
    public void updateUser(@RequestBody UserModel body, @PathVariable Integer id) {
        userService.updateUser(body, id);
    }
}
