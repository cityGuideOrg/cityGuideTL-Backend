package com.cityGuideTL.backend.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "city")
public class CityModel implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String placeId;
    private String longitude;
    private String latitude;
    private String woe_name;
    private String content;

    @OneToMany(mappedBy = "cityModel", cascade = CascadeType.ALL)
    Set<PhotoModel> photoModels = new HashSet<>();

    public CityModel() {
    }

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

    public Set<PhotoModel> getPhotoModels() {
        return photoModels;
    }

    public void setPhotoModels(Set<PhotoModel> photoModels) {
        this.photoModels = photoModels;
    }
}
