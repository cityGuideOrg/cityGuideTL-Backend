package com.cityGuideTL.backend.Entities;


import pember.kmeans.geo.GeographicPoint;

import java.math.BigDecimal;

public class Point implements GeographicPoint {

    private BigDecimal latitude;
    private BigDecimal longitude;

    public Point(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Point(String latitude, String longitude) {
        this.latitude = new BigDecimal(latitude);
        this.longitude = new BigDecimal(longitude);
    }

    @Override
    public BigDecimal getLatitude() {
        return latitude;
    }

    @Override
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Override
    public BigDecimal getLongitude() {
        return longitude;
    }

    @Override
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = new BigDecimal(latitude);
    }

    public void setLongitude(String longitude) {
        this.longitude = new BigDecimal(longitude);
    }
}
