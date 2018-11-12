package com.cityGuideTL.backend.Models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "point")
public class PointModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Integer id;
    private String pointName;
    private BigDecimal longitude;
    private BigDecimal latitude;

    public Integer getId() {
        return id;
    }
    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

}
