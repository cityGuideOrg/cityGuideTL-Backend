package com.cityGuideTL.backend.Dao;

import com.cityGuideTL.backend.Models.City;
import com.cityGuideTL.backend.Repository.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository("CityMySqlDao")
public class cityMySqlDao {
    @Autowired
    private CitiesRepository citiesRepository;

    public void saveCity(City city){
        citiesRepository.save(city);
    }

    public void deleteCity(Integer id){
        City city;
        try {
            city = citiesRepository.findById(id).orElseThrow(IOException::new);
        } catch (IOException e) {

            System.out.println("city not found");
            city=null;
        }
        citiesRepository.delete(city);
    }

    public City getCityById(Integer id){
        City city;
        try {
            city = citiesRepository.findById(id).orElseThrow(IOException::new);
        } catch (IOException e) {
            System.out.println("City not found");
            city = null;
        }
        return city;
    }

    public List<City> getAllCities(){
        return citiesRepository.findAll();
    }




}
