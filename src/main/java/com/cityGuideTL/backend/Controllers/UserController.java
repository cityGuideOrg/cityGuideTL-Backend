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

    @PostMapping(path="/add")
    public @ResponseBody
    User addUser(@RequestBody User body) {
        User user = new User();
        user.setPassword(body.getPassword());
        userRepository.save(body);
        return body;
    }

    @PostMapping(path="/delete")
    public @ResponseBody
    void deleteUser(@RequestParam Integer id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    @GetMapping(path="/all")
    public List<User> getAll() {
        List<User> list = userRepository.findAll();
        return list;
    }
}
