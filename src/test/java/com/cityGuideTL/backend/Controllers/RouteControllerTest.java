package com.cityGuideTL.backend.Controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cityGuideTL.backend.Entities.Cost;
import com.cityGuideTL.backend.Entities.Point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteControllerTest {

    private Cost cost;
    private Point startingPoint;
    private List<Point> pointsList;

    @Autowired
    private RouteController routeController;

    @Test
    public void contexLoads() throws Exception {
        assertNotNull(routeController);
    }
    
    @Test
    public void routeShouldBeGoingThroughDifferentPoints() {
        cost = new Cost();
        startingPoint = new Point("11.20471", "-61.98753");
        pointsList = new ArrayList<Point>(Arrays.asList(startingPoint, startingPoint, startingPoint));
        cost = routeController.Visit(startingPoint, pointsList, null);
        assertNotNull(cost);
    }

    @Test
    public void routeShouldIncludeAllOfThePointsProvidedForAFullRoute() {
        cost = new Cost();
        startingPoint = new Point("11.20471", "-61.98753");
        pointsList = new ArrayList<Point>();
        pointsList.add(new Point("11.20245", "-61.98444"));
        pointsList.add(new Point("11.20222", "11.10000"));
        pointsList.add(new Point("11.10000","10.20471"));
        cost = routeController.Visit(startingPoint, pointsList, null);
        boolean isEqual = false;
        for(Point pointOfActual : pointsList) {
            for(Point pointOfExpected : cost.pointsTaken) {
                if(pointOfExpected.getLatitude().equals(pointOfExpected.getLatitude()) && 
                pointOfExpected.getLongitude().equals(pointOfExpected.getLongitude())) 
                    isEqual=true;
            }
            if(isEqual == false) {
                fail();
            }else
                isEqual = false;
        }
        
    }

    @Test
    public void ifProvidedWithOnePointDestination() {
        cost = new Cost();
        startingPoint = new Point("11.20471", "-61.98753");
        pointsList = new ArrayList<Point>();
        pointsList.add(new Point("11.20234", "-61.97753"));
        cost = routeController.Visit(startingPoint, pointsList, null);
        assertNotNull(cost);
    }
}
