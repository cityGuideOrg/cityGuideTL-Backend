package com.cityGuideTL.backend.Services;



import com.cityGuideTL.backend.Models.CityModel;
import com.cityGuideTL.backend.Repository.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


public class CityService {
    @Autowired
    private CitiesRepository citiesRepository;


    public void addCity(@RequestBody CityModel city){
        citiesRepository.save(city);
    }


    public void deleteCity(@PathVariable Integer id) {
        CityModel city;
        try {
            city = citiesRepository.findById(id).orElseThrow(IOException::new);
        } catch (IOException e) {

            System.out.println("city not found");
            city=null;
        }
        citiesRepository.delete(city);
    }


    public CityModel getCity(@PathVariable Integer id) {
        CityModel city;
        try {
            city = citiesRepository.findById(id).orElseThrow(IOException::new);
        } catch (IOException e) {
            System.out.println("City not found");
            city = null;
        }
        return city;
    }


    public void updateCity(@RequestBody CityModel city) {
        //do updates with best practice which is unknown yet.
        //write validation checks
    }

    public List<CityModel> getAll(){
        return citiesRepository.findAll();
    }




}
