package com.cityGuideTL.backend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.util.ArrayList;


@JsonComponent
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
    @JsonProperty
    private String place_id;
    @JsonProperty
    private String latitude;
    @JsonProperty
    private String longitude;
    @JsonProperty
    private String woe_name;
    @JsonProperty("_content")
    private String content;

    private ArrayList<Photo> topFivePhotos = new ArrayList<>();


    public City() {
    }

    public City(String place_id,String latitude, String longitude, String woe_name, String content) {
        this.place_id = place_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.woe_name = woe_name;
        this.content = content;
    }

    public String getPlace_id() {
        return place_id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getWoe_name() {
        return woe_name;
    }

    public String getContent() {
        return content;
    }

    public ArrayList<Photo> getTopFivePhotos() {
        return topFivePhotos;
    }

    public void setTopFivePhotos(ArrayList<Photo> topFivePhotos) {
        this.topFivePhotos = topFivePhotos;
    }

    public void addATopPhoto(Photo topPhoto){
        this.topFivePhotos.add(topPhoto);
    }

}
