package com.cityGuideTL.backend.Controllers;

import com.cityGuideTL.backend.Models.CityModel;
import com.cityGuideTL.backend.Services.CityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cityGuideTL.backend.Repository.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/city")
public class CityController {
    @Autowired
    private CitiesRepository citiesRepository;
    private CityService cityService = new CityService();

    @PostMapping
    @ResponseBody
    public void addCity(@RequestBody CityModel city){
        cityService.addCity(city);
    }

    @DeleteMapping(path="/{id}")
    @ResponseBody
    public void deleteCity(@PathVariable Integer id) {
        cityService.deleteCity(id);
    }

    @GetMapping(path="/{id}")
    @ResponseBody
    public CityModel getCity(@PathVariable Integer id) {
       return cityService.getCity(id);
    }


    public void updateCity(@RequestBody CityModel city) {
        cityService.updateCity(city);
    }
    @GetMapping
    @ResponseBody
    public List<CityModel> getAll(){
        return cityService.getAll();
    }




}

