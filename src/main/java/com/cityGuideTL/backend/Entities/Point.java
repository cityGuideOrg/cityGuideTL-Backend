package com.cityGuideTL.backend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.math.BigDecimal;
@JsonComponent
public class Point {
@JsonProperty
    private BigDecimal latitude;
    @JsonProperty

    private BigDecimal longitude;

    public Point() {

    }

    public Point(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Point(String latitude, String longitude) {
        this.latitude = new BigDecimal(latitude);
        this.longitude = new BigDecimal(longitude);
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = new BigDecimal(latitude);
    }

    public void setLongitude(String longitude) {
        this.longitude = new BigDecimal(longitude);
    }

    public double distance(Point point) {

        return Point.distance(latitude.floatValue(), point.latitude.floatValue(), longitude.floatValue(), point.longitude.floatValue(), 1, 1);

        //return point.latitude.subtract(this.latitude).pow(2).add(point.longitude.subtract(this.longitude).pow(2)).floatValue();
    }

    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}
