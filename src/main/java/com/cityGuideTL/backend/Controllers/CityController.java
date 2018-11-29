package com.cityGuideTL.backend.Controllers;

import com.cityGuideTL.backend.Models.CityModel;
import com.cityGuideTL.backend.Services.CityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/city")
public class CityController {
    @Autowired
    private CityService cityService;


    //:8080/city _POST request give body
    @PostMapping
    @ResponseBody
    public void addCity(@RequestBody CityModel city){
        cityService.addCity(city);
    }

    //:8080/city/{id} _DELETE request give pathvar id Int
    @DeleteMapping(path="/{id}")
    public void deleteCity(@PathVariable Integer id) {
        cityService.deleteCity(id);
    }

    //:8080/city/{id} _GET request give id also
    @GetMapping(path="/{id}")
    @ResponseBody
    public CityModel getCity(@PathVariable Integer id) {
       return cityService.getCity(id);
    }



    @PostMapping(path="/{id}")
    public void updateCity(@PathVariable Integer id, @RequestBody CityModel city) {
        cityService.updateCity(id, city);
    }

    @GetMapping
    @ResponseBody
    public List<CityModel> getAll(){
        return cityService.getAll();
    }

}

