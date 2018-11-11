package com.cityGuideTL.backend.Controllers;

import com.cityGuideTL.backend.Entities.BestRouteModel;
import com.cityGuideTL.backend.Entities.Cost;
import com.cityGuideTL.backend.Entities.Point;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(path="/route")
public class RouteController {


    public Cost Visit(Point from, List<Point> where, Cost cost) {
        List<Cost> costs = new LinkedList<Cost>(); //linked list of all costs

        //first run-through
        if(cost == null) {
            cost = new Cost();
            cost.pointsTaken = new LinkedList<Point>(); //points visited
            cost.pointsTaken.add(from); //you add the starting point so it will be not visited
            cost.cost = 0; //no cost for starting point
        } else {
            List<Point> pointsTaken = cost.pointsTaken.subList(0, cost.pointsTaken.size());
            pointsTaken.add(from);
            double distanceCost = cost.cost;

            cost = new Cost();
            cost.cost = distanceCost;
            cost.pointsTaken = pointsTaken;

            if(cost.pointsTaken.size() == where.size() + 1) {
                return cost;
            }
         //   cost.cost += 5;
        }


        //where is the places that we want to visit
        for(Point point : where)  {
            int taken = 0;
            for(Point visitedPlaces : cost.pointsTaken) {

                if(visitedPlaces.getLatitude().equals(point.getLatitude()) && visitedPlaces.getLongitude().equals(point.getLongitude())) {
                    taken = 1;
                    break;
                }

            }

            if(taken == 1)
                continue;

            cost.cost += from.distance(point);

            Cost newCost = Visit(point, where, cost);

            if(newCost != null && newCost.pointsTaken.size() == where.size() + 1) {

                ((LinkedList<Cost>) costs).addLast(newCost);
            }

        }





        double minCost = -1;
        Cost minValue = null;

        for(Cost all_cost : costs) {
            if( minCost == -1 || all_cost.cost < minCost ) {
                minValue = all_cost;
                minCost = all_cost.cost;
            }
        }



        return minValue;





    }




    @PostMapping(path="/findBestRoute")
    public @ResponseBody
    Cost addUser(@RequestBody BestRouteModel body) {


        Cost stuff = Visit(body.startingPoint, body.placesNeedsToBeVisited, null);



        return stuff;
    }




}
