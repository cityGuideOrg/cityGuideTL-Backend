package com.cityGuideTL.backend.Models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "city")
public class Cities {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column()
    private  int id;


    private String place_id;
    private String latitude;
    private String longitude;
    private String woe_name;
    private String content;

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
