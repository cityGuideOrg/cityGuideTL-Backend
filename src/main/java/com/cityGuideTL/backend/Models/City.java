package com.cityGuideTL.backend.Models;

import com.cityGuideTL.backend.Entities.Photo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
//    @Column(updatable = false, nullable = false)
    private Integer id;
    private String placeId;
    private String longitude;
    private String latitude;
    private String woe_name;
    private String content;

    @ElementCollection
    @CollectionTable(name = "TopFivePhotos" , joinColumns =@JoinColumn(name = "placeId"))
    @Column(name = "Photo")
    private List<Photo> topFivePhotos = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getWoe_name() {
        return woe_name;
    }

    public void setWoe_name(String woe_name) {
        this.woe_name = woe_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Photo> getTopFivePhotos() {
        return topFivePhotos;
    }

    public void setTopFivePhotos(ArrayList<Photo> topFivePhotos) {
        this.topFivePhotos = topFivePhotos;
    }
}
