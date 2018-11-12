package com.cityGuideTL.backend.Controllers;

import com.cityGuideTL.backend.Models.UserModel;
import com.cityGuideTL.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//to fix validations
@RestController
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    //get all users
    @GetMapping
    @ResponseBody
    public List<UserModel> getAll() {
        List<UserModel> list = userRepository.findAll();
        return userRepository.findAll();
    }

    //create a user
    @PostMapping
    @ResponseBody
    public void addUser(@RequestBody UserModel body) {
        UserModel userModel = new UserModel();
        userModel.setPassword(body.getPassword());
        userRepository.save(body);
    }

    //get user with specified id
    @GetMapping(path="/{id}")
    @ResponseBody
    public UserModel getUser(@PathVariable Integer id) {
        UserModel userModel = userRepository.findById(id).get();
        return userModel;
    }

    //delete a user with specified id
    @DeleteMapping(path="/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable Integer id) {
        UserModel userModel = userRepository.findById(id).get();
        userRepository.delete(userModel);
    }

    @PutMapping(path="/{id}")
    @ResponseBody
    public void updateUser(@RequestBody UserModel body, @PathVariable Integer id) {
        //you cant simply replace the object because the null id is replaced also
        //you either need to get an id on the body and replace the whole user and save it or just replace the names etc.
        UserModel userModelToUpdate = userRepository.getOne(id);
        userModelToUpdate.setFirstname(body.getFirstname());
        if(body.getPassword()!=null) {
            //validate on UserModel entity
        }
        userModelToUpdate.setLastname(body.getLastname());
        userModelToUpdate.setEmail(body.getEmail());
        userModelToUpdate.setPassword(body.getPassword());
        userModelToUpdate.setUsername(body.getUsername());
        userRepository.save(body);
        //wait for best practice
    }
}
