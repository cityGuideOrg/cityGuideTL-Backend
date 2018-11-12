package com.cityGuideTL.backend.Models;

import com.cityGuideTL.backend.Entities.Point;
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
