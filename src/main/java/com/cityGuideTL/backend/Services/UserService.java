package com.cityGuideTL.backend.Services;

import com.cityGuideTL.backend.Models.UserModel;
import com.cityGuideTL.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
//todo: access jpaapi through this service, commit failcheck code
//todo: map entities to models
public class UserService {
    @Autowired
    UserRepository userRepository;
    //get all users

    public List<UserModel> getAll() {
        List<UserModel> list = userRepository.findAll();
        return userRepository.findAll();
    }

    //create a user

    public void addUser(UserModel body) {
        UserModel userModel = new UserModel();
        userModel.setPassword(body.getPassword());
        userRepository.save(body);
    }

    //get user with specified id
    public UserModel getUser(Integer id) {
        UserModel userModel = userRepository.findById(id).get();
        return userModel;
    }

    //delete a user with specified id
    public void deleteUser(Integer id) {
        UserModel userModel;
        try {
            userModel = userRepository.findById(id).orElseThrow(IOException::new);
        }
        catch (IOException e) {
            System.out.println("User not found");
            userModel = null;
        }
        userRepository.delete(userModel);
    }

    public void updateUser(UserModel body, Integer id) {
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
