package com.cityGuideTL.backend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

import java.util.List;

@JsonComponent
public class BestRouteModel {

    @JsonProperty
    public Point startingPoint;

    @JsonProperty
    public List<Point> placesNeedsToBeVisited;

}
