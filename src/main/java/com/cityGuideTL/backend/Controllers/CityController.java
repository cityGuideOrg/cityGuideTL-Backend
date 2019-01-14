package com.cityGuideTL.backend.Controllers;

import com.cityGuideTL.backend.Entities.City;
import com.cityGuideTL.backend.Models.CityModel;
import com.cityGuideTL.backend.Services.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path="/city")
public class CityController {

    @Autowired
    private CityService cityService;


    //:8080/city _POST request give body
    @PostMapping
    @ResponseBody
    public ResponseEntity<Void> addCity(@RequestBody City city){
        cityService.addCity(city);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "").buildAndExpand("").toUri();
        return ResponseEntity.created(location).build();
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

