package com.cityGuideTL.backend.Services;

import com.cityGuideTL.backend.Models.UserModel;
import com.cityGuideTL.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
//todo: access jpaapi through this service, commit failcheck code
//todo: map entities to models
//todo: create user entity for json handling

@Service
public class UserService implements UserDetailsService {
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
        // wait for best practice
    }


    //TODO:implemented method from interface userRepository
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());	
    }

    //TODO: defined authorities
    private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}


}
