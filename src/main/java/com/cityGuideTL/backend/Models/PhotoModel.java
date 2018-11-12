package com.cityGuideTL.backend.Models;

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class PhotoModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String photoID;
    private String longitude;
    private String latitude;
    private int totalNearPhotos;

    @ManyToOne
    @JoinColumn
    private CityModel cityModel;

    public PhotoModel() {
    }

    public PhotoModel(String photoID, String longitude, String latitude, int totalNearPhotos, CityModel cityModel) {
        this.photoID = photoID;
        this.longitude = longitude;
        this.latitude = latitude;
        this.totalNearPhotos = totalNearPhotos;
        this.cityModel = cityModel;
    }

    public String getPhotoID() {
        return photoID;
    }

    public void setPhotoID(String photoID) {
        this.photoID = photoID;
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

    public int getTotalNearPhotos() {
        return totalNearPhotos;
    }

    public void setTotalNearPhotos(int totalNearPhotos) {
        this.totalNearPhotos = totalNearPhotos;
    }

}
