package com.cityGuideTL.backend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.util.ArrayList;


@JsonComponent

public class CitiesList {
    @JsonProperty("place")
    private ArrayList<City> cityList;
    @JsonIgnore
    @JsonProperty
    private String query;
    @JsonIgnore
    @JsonProperty
    private String total;
    public CitiesList() {
        cityList = new ArrayList<>();
    }

    public CitiesList(ArrayList<City> cityList) {
        this.cityList = cityList;
    }

    public ArrayList<City> getCityList() {
        return cityList;
    }
    public City getCityFromList(int id) {
        return cityList.get(id);
    }

    public void addCityToList(City city){
        cityList.add(city);
    }

    public void setCityList(ArrayList<City> cityList) {
        this.cityList = cityList;
    }
}
