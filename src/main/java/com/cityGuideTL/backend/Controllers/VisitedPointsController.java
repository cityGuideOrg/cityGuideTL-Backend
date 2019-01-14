package com.cityGuideTL.backend.Controllers;

import com.cityGuideTL.backend.Models.CityModel;
import com.cityGuideTL.backend.Models.UserModel;
import com.cityGuideTL.backend.Models.VisitedPointsModel;
import com.cityGuideTL.backend.Repository.UserRepository;
import com.cityGuideTL.backend.Services.CityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cityGuideTL.backend.Services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/visitedpoints")
public class VisitedPointsController {
    @Autowired
    private VisitedPointsService visitedPointsService;

    @Autowired
    private UserRepository userRepository;


    //:8080/city _POST request give body
    @PostMapping
    @ResponseBody
    public VisitedPointsModel addVisitedPoint(@RequestBody VisitedPointsModel city, @AuthenticationPrincipal User user){
        city.setUser_id(userRepository.findByUsername(user.getUsername()).getId());

        return visitedPointsService.addVisitedPoint(city);
    }

    //:8080/city/{id} _DELETE request give pathvar id Int
    @DeleteMapping(path="/{id}")
    public void deleteVisitedPoint(@PathVariable Integer id) {
        visitedPointsService.deleteVisitedPoint(id);
    }

    @GetMapping
    @ResponseBody
    public List<VisitedPointsModel> getAll(@AuthenticationPrincipal User user){
        UserModel usera = userRepository.findByUsername(user.getUsername());
        return visitedPointsService.getVisitedPointByUserId(usera.getId());
    }
}

